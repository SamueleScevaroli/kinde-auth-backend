package com.tutorial.general.order.domain.user.vo;

import com.tutorial.general.shared.error.domain.Assert;

public record UserFirstname(String value) {

  public UserFirstname {
    Assert.field("value", value).maxLength(255);
  }
}