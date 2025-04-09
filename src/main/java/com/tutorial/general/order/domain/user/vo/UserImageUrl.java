package com.tutorial.general.order.domain.user.vo;

import com.tutorial.general.shared.error.domain.Assert;

public record UserImageUrl(String value) {

  public UserImageUrl {
    Assert.field("value", value).maxLength(1000);
  }
}