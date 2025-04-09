package com.tutorial.general.order.domain.user.vo;

import com.tutorial.general.shared.error.domain.Assert;

public record UserEmail(String value) {

  public UserEmail {
    Assert.field("value", value).maxLength(255);
  }
}