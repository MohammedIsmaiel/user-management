package com.mohammedismaiel.usermanagement.app.util;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Payload;
import com.auth0.jwt.exceptions.JWTVerificationException;
import static com.mohammedismaiel.usermanagement.app.constant.SecurityConstant.*;
import com.mohammedismaiel.usermanagement.app.domain.User;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTTokenUtil {

    private final String secret;

    JWTTokenUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String extractUsername(String token) {
        // return getJWTVerifier().verify(token).getSubject();
        return extractClaim(token, Payload::getSubject);
    }

    public <T> T extractClaim(String token, Function<Payload, T> claimer) {
        return claimer.apply(getJWTVerifier().verify(token));
    }

    public String generateToken(User user) {
        return JWT.create().withIssuer(MINE).withAudience(ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(user.getUsername())
                .withArrayClaim(AUTHORITIES, user.getAuthorities().toArray(String[]::new))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(MINE).build();
        } catch (Exception e) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return !username.isEmpty() && username.equalsIgnoreCase(extractUsername(token))
                && !isTokenExpired(verifier, token);
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = extractClaim(token, Payload::getExpiresAt);
        return expiration.before(new Date());
    }

    public Authentication getAuthentication(String username, List<? extends GrantedAuthority> authorities,
            HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }
}
