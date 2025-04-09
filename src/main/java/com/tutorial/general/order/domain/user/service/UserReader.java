package com.tutorial.general.order.domain.user.service;

import com.tutorial.general.order.domain.user.aggregate.User;
import com.tutorial.general.order.domain.user.repository.UserRepository;
import com.tutorial.general.order.domain.user.vo.UserEmail;
import com.tutorial.general.order.domain.user.vo.UserPublicId;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserReader {

  private final UserRepository userRepository;

  public Optional<User> getByEmail(UserEmail userEmail) {
    return userRepository.getOneByEmail(userEmail);
  }

  public Optional<User> getByPublicId(UserPublicId userPublicId) {
    return userRepository.get(userPublicId);
  }
}