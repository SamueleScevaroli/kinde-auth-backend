package com.tutorial.general.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserAddressToUpdate(@NotNull UUID userPublicId,
                                  @NotNull UserAddress userAddress) {
}