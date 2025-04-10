package com.tutorial.general.auth.controller;

import com.tutorial.general.auth.dto.UserDto;
import com.tutorial.general.auth.mapper.UserMapper;
import com.tutorial.general.auth.model.User;
import com.tutorial.general.auth.service.UsersApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersApplicationService usersApplicationService;
    private final UserMapper userMapper;

    @GetMapping("/authenticated")
    public UserDto getAuthenticatedUser(@AuthenticationPrincipal Jwt jwtToken,
                                        @RequestParam boolean forceResync) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUserWithSync(jwtToken, forceResync);
        return userMapper.toDto(authenticatedUser);
    }
}