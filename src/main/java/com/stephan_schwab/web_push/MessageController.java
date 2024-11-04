package com.stephan_schwab.web_push;

import java.security.Security;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@RestController
@RequestMapping("/send-message")
public class MessageController {
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  private PushService pushService;

  public MessageController() throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    pushService = new PushService();
    pushService.setPublicKey(PublicPrivateKey.PUBLIC_KEY);
    pushService.setPrivateKey(PublicPrivateKey.PRIVATE_KEY);
    pushService.setSubject("mailto:example_email@example.com");
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<Object> sendMessage(@RequestBody Message message) throws Exception {
    LOGGER.info("sendMessage: {}", message);

    String payload = "{\"title\":\"" + message.getTitle() + "\",\"message\":\"" + message.getMessage()
        + "\",\"interaction\":\"" + message.isInteraction() + "\"}";

    Notification notification = new Notification(
        message.getPushSubscription().getEndpoint(),
        message.getPushSubscription().getKeys().getP256dh(),
        message.getPushSubscription().getKeys().getAuth(),
        payload);

    int delay = message.getDelay();

    scheduler.schedule(() -> {
      try {
        pushService.send(notification);
        System.out.println("Notification sent successfully");
      } catch (Exception e) {
        System.out.println("Notification error: " + e.getMessage());
      }
    }, delay, TimeUnit.MILLISECONDS);

    return ResponseEntity.status(201).build();
  }

}
