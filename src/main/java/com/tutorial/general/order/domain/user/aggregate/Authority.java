package com.tutorial.general.order.domain.user.aggregate;

import com.tutorial.general.order.domain.user.vo.AuthorityName;
import com.tutorial.general.shared.error.domain.Assert;
import lombok.Builder;

@Builder
public class Authority {

  private AuthorityName name;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("name", authorityName);
    this.name = authorityName;
  }

  public AuthorityName getName() {
    return name;
  }
}