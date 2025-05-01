package com.tutorial.general.auth.kinde;

import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserReader {

  private final UserService userService;

  public Optional<User> getByEmail(String userEmail) {
    return userService.getOneByEmail(userEmail);
  }
}