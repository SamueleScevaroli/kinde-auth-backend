package com.tutorial.general.auth.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@Getter
@Data
public class User {

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Setter
    private UUID publicId;

    @Size(max = 1000)
    private String imageUrl;

    private Instant lastModifiedDate;
    private Instant createdDate;

    @NotNull
    private Set<String> authorities;

    private Long dbId;
    private Instant lastSeen;

    public void updateFromUser(User user) {
        this.email = user.email;
        this.imageUrl = user.imageUrl;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
    }

    public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
        UserBuilder userBuilder = User.builder();

        if (attributes.containsKey("preferred_email")) {
            userBuilder.email(attributes.get("preferred_email").toString());
        }

        if (attributes.containsKey("last_name")) {
            userBuilder.lastName(attributes.get("last_name").toString());
        }

        if (attributes.containsKey("first_name")) {
            userBuilder.firstName(attributes.get("first_name").toString());
        }

        if (attributes.containsKey("picture")) {
            userBuilder.imageUrl(attributes.get("picture").toString());
        }

        if (attributes.containsKey("last_signed_in")) {
            userBuilder.lastSeen(Instant.parse(attributes.get("last_signed_in").toString()));
        }

        Set<String> authorities = new HashSet<>(rolesFromAccessToken);

        userBuilder.authorities(authorities);

        return userBuilder.build();
    }
}