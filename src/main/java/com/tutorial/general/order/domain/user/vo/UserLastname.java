package com.tutorial.general.order.domain.user.vo;

import com.tutorial.general.shared.error.domain.Assert;

public record UserLastname(String value) {

  public UserLastname {
    Assert.field("value", value).maxLength(255);
  }
}