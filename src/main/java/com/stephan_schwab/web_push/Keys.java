package com.stephan_schwab.web_push;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Keys {

  private String auth;
  private String p256dh;

  public Keys(String auth, String p256dh) {
    this.auth = auth;
    this.p256dh = p256dh;
  }

  public String getAuth() {
    return auth;
  }

  public String getP256dh() {
    return p256dh;
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
