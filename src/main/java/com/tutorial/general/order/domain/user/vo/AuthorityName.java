package com.tutorial.general.order.domain.user.vo;

import com.tutorial.general.shared.error.domain.Assert;

public record AuthorityName(String name) {

  public AuthorityName {
    Assert.field("name", name).notNull();
  }
}