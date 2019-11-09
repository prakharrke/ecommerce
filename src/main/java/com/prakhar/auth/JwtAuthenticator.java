package com.prakhar.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.*;
import com.prakhar.model.Person;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, User> {

    Key key;

    public JwtAuthenticator(Key key) {
        this.key = key;
    }

    @Override
    public Optional<User> authenticate(String token) throws AuthenticationException {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            User user;
            Claims claims = claimsJws.getBody();
            user = new User(
                    claims.getSubject().toString(),
                    Long.parseLong(claims.get("personId").toString())

            );
            return Optional.of(user);
        }catch (Exception e){
            return Optional.empty();
        }
    }


    public String generateJwtToken(Person person) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(person.getEmail())
                .signWith(SignatureAlgorithm.HS512, key)
                .setIssuedAt(new Date());
        jwtBuilder.claim("personId", person.getId().toString());

        return jwtBuilder.compact();
    }
}
