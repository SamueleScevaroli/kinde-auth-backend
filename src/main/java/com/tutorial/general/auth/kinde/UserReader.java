package com.tutorial.general.auth.kinde;

import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserReader {

  private final UserService userService;

  public Optional<User> getByEmail(String userEmail) {
    return userService.getOneByEmail(userEmail);
  }

  public Optional<User> getByPublicId(UUID userPublicId) {
    return userService.get(userPublicId);
  }
}