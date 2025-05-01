package com.tutorial.general.auth.kinde;

import com.tutorial.general.auth.model.AuthenticatedUser;
import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserSynchronizer {

    private final UserService userService;
    private final KindeService kindeService;

    private static final String UPDATE_AT_KEY = "last_signed_in";

    public void syncWithIdp(Jwt jwtToken, boolean forceResync) {
        Map<String, Object> claims = jwtToken.getClaims();
        List<String> rolesFromToken = AuthenticatedUser.extractRolesFromToken(jwtToken);
        Map<String, Object> userInfo = kindeService.getUserInfo(claims.get("sub").toString());
        User user = User.fromTokenAttributes(userInfo, rolesFromToken);
        Optional<User> existingUser = userService.getOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (claims.get(UPDATE_AT_KEY) != null) {
                Instant lastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate = Instant.ofEpochSecond((Integer) claims.get(UPDATE_AT_KEY));

                if (idpModifiedDate.isAfter(lastModifiedDate) || forceResync) {
                    updateUser(user, existingUser.get());
                }
            }
        } else {
            user.setPublicId(UUID.randomUUID());
            userService.save(user);
        }

    }

    private void updateUser(User user, User existingUser) {
        existingUser.updateFromUser(user);
        userService.save(existingUser);
    }
}