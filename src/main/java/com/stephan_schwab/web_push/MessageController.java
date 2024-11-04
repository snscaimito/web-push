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

  private static final String PUBLIC_KEY = "BGByiWdMxciiNJkqcAzGoZpS4JHmhKZsjWXNvte52AqXd_8ACgNL2iFG6L-VLEq3vleg2bM8MuW7Hb3P85cA_Qo";
  private static final String PRIVATE_KEY = "zQDqrROWVmzNCGwIoGn-2HyzVi15KhpziZ7gsMOVUBg";

  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  private PushService pushService;

  public MessageController() throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    pushService = new PushService();
    pushService.setPublicKey(PUBLIC_KEY);
    pushService.setPrivateKey(PRIVATE_KEY);
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
