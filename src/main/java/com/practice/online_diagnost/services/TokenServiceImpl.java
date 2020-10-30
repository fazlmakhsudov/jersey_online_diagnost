package com.practice.online_diagnost.services;

import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class TokenServiceImpl implements TokenService {
    private static final Logger LOGGER = Logger.getLogger("TokenServiceImpl");
    private static TokenService tokenService;
    private final Key key;
    private final UserService userService;

    private TokenServiceImpl() {
        userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe(e.getMessage());
        }
        key = keyGenerator.generateKey();
    }

    public static TokenService getInstance() {
        if (Objects.isNull(tokenService)) {
            tokenService = new TokenServiceImpl();
        }
        return tokenService;
    }


    @Override
    public String generate(String email) {
        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    @Override
    public String generate(String email, int id) {
        String jwtToken = Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    @Override
    public boolean validate(String token) {
        boolean validationFlag = true;
        try {

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            if (claims.getExpiration().compareTo(new Date()) < 0 ||
                    Objects.isNull(userService.find(claims.getSubject()))) {
                validationFlag = false;
            }
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            validationFlag = false;
        }
        return validationFlag;
    }

    @Override
    public int getId(String token) {
        int id = -1;
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            id = (int) claims.get("id");
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return id;
    }

    @Override
    public String getEmail(String token) {
        String email = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            email = claims.getSubject();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return email;
    }

    private static Date toDate(LocalDateTime plusMinutes) {
        ZonedDateTime zonedDateTime = plusMinutes.atZone(ZoneOffset.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
