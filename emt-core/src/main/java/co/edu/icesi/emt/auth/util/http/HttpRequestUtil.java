package co.edu.icesi.emt.auth.util.http;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.security.jwt.JWTProvider;

@Component
public class HttpRequestUtil {

    private final JWTProvider jwtProvider;

    @Autowired
    public HttpRequestUtil(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public String getUserIdFromRequest(final HttpServletRequest httpRequest) {
        return jwtProvider.getUsernameFromJWT(this.extractAuthenticationToken(httpRequest));
    }

    private String extractAuthenticationToken(final HttpServletRequest httpRequest) {
        return httpRequest.getHeader("Authorization").replace("Bearer ", "");
    }
}