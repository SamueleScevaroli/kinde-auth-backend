package com.tutorial.general.auth.dto;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserDto(UUID publicId,
                      String firstName,
                      String lastName,
                      String email,
                      String imageUrl,
                      Set<String> authorities) {

}