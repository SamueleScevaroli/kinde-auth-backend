package com.tutorial.general.auth.service;

import com.tutorial.general.auth.model.User;

import java.util.Optional;

public interface UserService {

  void save(User user);

  Optional<User> getOneByEmail(String userEmail);

}