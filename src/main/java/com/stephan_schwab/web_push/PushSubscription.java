package com.stephan_schwab.web_push;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PushSubscription {
  private String endpoint;
  private Keys keys;

  public PushSubscription(String endpoint, Keys keys) {
    this.endpoint = endpoint;
    this.keys = keys;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public Keys getKeys() {
    return keys;
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
