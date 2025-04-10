package com.tutorial.general.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserAddress(@NotNull String street,
                          @NotNull String city,
                          @NotNull String zipCode,
                          @NotNull String country) {
}