package com.stephan_schwab.web_push;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public-key")
public class PublicKeyController {
  private Logger LOGGER = LoggerFactory.getLogger(PublicKeyController.class);

  @GetMapping(produces = "application/json")
  public ResponseEntity<Object> getPublicKey() {
    LOGGER.info("getPublicKey");
    return ResponseEntity.ok().body(Collections.singletonMap("publicKey", PublicPrivateKey.PUBLIC_KEY));
  }
}
