package com.tutorial.general.auth.service.impl;

import com.tutorial.general.auth.mapper.UserMapper;
import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.model.UserAddressToUpdate;
import com.tutorial.general.auth.repository.UserRepository;
import com.tutorial.general.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(@Valid User user) {
        if (user.getDbId() != null) {
            userRepository.findById(user.getDbId())
                    .ifPresent(u -> {
                        u.updateFromUser(user);
                        userRepository.saveAndFlush(u);
                    });
        } else {
            userRepository.save(userMapper.toEntity(user));
        }
    }

    @Override
    public Optional<User> get(UUID userPublicId) {
        return userRepository.findOneByPublicId(userPublicId)
                .map(userMapper::toModel);
    }

    @Override
    public Optional<User> getOneByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(userMapper::toModel);
    }

    @Override
    public void updateAddress(UUID userPublicId, UserAddressToUpdate userAddress) {
        userRepository.updateAddress(userPublicId,
                userAddress.userAddress().street(),
                userAddress.userAddress().city(),
                userAddress.userAddress().country(),
                userAddress.userAddress().zipCode());
    }
}