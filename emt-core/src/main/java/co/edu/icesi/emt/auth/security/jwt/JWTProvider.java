package co.edu.icesi.emt.auth.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.security.entity.MainUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JWTProvider { // Generate a JWT and validate if the tokes is formed correctly, and other
                           // things

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateJWT(Authentication authentication) {
        MainUser mainUser = (MainUser) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUsernameFromJWT(String token) {

        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJWT(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("JWT malformed");
        } catch (UnsupportedJwtException e) {
            log.error("JWT unsupported");
        } catch (ExpiredJwtException e) {
            log.error("JWT expired");
        } catch (IllegalArgumentException e) {
            log.error("JWT empty");
        } catch (SecurityException e) {
            log.error("Fail with signature");
        }
        return false;
    }
}
