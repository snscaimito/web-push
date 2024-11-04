package com.stephan_schwab.web_push;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Message {

  private int delay;
  private boolean interaction;
  private String message;
  private String title;
  private PushSubscription pushSubscription;

  public Message(int delay, boolean interaction, String message, String title, PushSubscription pushSubscription) {
    this.delay = delay;
    this.interaction = interaction;
    this.message = message;
    this.title = title;
    this.pushSubscription = pushSubscription;
  }

  public int getDelay() {
    return delay;
  }

  public boolean isInteraction() {
    return interaction;
  }

  public String getMessage() {
    return message;
  }

  public String getTitle() {
    return title;
  }

  public PushSubscription getPushSubscription() {
    return pushSubscription;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
