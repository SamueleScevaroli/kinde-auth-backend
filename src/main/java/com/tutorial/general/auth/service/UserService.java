package com.tutorial.general.auth.service;

import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.model.UserAddressToUpdate;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

  void save(User user);

  Optional<User> get(UUID userPublicId);

  Optional<User> getOneByEmail(String userEmail);

  void updateAddress(UUID userPublicId, UserAddressToUpdate userAddress);

}