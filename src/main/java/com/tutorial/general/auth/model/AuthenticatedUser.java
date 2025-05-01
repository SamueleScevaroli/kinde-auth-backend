package com.tutorial.general.auth.model;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import com.tutorial.general.auth.exceptions.NotAuthenticatedUserException;
import com.tutorial.general.auth.exceptions.UnknownAuthenticationException;
import com.tutorial.general.util.assertions.Assert;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public final class AuthenticatedUser {

    public static final String PREFERRED_USERNAME = "email";

    public static Username username() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(AuthenticatedUser::readPrincipal)
                .flatMap(Username::of)
                .orElseThrow(NotAuthenticatedUserException::new);
    }

    /**
     * Read user principal from authentication
     *
     * @param authentication authentication to read the principal from
     * @return The user principal
     * @throws UnknownAuthenticationException if the authentication can't be read (unknown token type)
     */
    public static String readPrincipal(Authentication authentication) {
        Assert.notNull("authentication", authentication);

        if (authentication.getPrincipal() instanceof UserDetails details) {
            return details.getUsername();
        }

        if (authentication instanceof JwtAuthenticationToken token) {
            return (String) token.getToken().getClaims().get(PREFERRED_USERNAME);
        }

        if (authentication.getPrincipal() instanceof DefaultOidcUser oidcUser) {
            return (String) oidcUser.getAttributes().get(PREFERRED_USERNAME);
        }

        if (authentication.getPrincipal() instanceof String principal) {
            return principal;
        }

        throw new UnknownAuthenticationException();
    }

    public static List<String> extractRolesFromToken(Jwt jwtToken) {
        List<LinkedTreeMap<String, String>> realmAccess =
                (List<LinkedTreeMap<String, String>>) jwtToken.getClaims().get("roles");
        return realmAccess.stream()
                .map(roleTreeMap -> roleTreeMap.get("key"))
                .toList();
    }
}
