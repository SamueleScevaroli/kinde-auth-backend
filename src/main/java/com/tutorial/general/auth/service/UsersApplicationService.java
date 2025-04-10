package com.tutorial.general.auth.service;

import com.tutorial.general.auth.kinde.KindeService;
import com.tutorial.general.auth.kinde.UserReader;
import com.tutorial.general.auth.kinde.UserSynchronizer;
import com.tutorial.general.auth.model.AuthenticatedUser;
import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.model.UserAddressToUpdate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

    private final UserSynchronizer userSynchronizer;
    private final UserReader userReader;

    public UsersApplicationService(UserService userService, KindeService kindeService) {
        this.userSynchronizer = new UserSynchronizer(userService, kindeService);
        this.userReader = new UserReader(userService);
    }

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync) {
        userSynchronizer.syncWithIdp(jwtToken, forceResync);
        return userReader.getByEmail(AuthenticatedUser.username().username())
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return userReader.getByEmail(AuthenticatedUser.username().username())
                .orElseThrow();
    }

    @Transactional
    public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
        userSynchronizer.updateAddress(userAddressToUpdate);
    }

}