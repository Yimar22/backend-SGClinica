package co.edu.icesi.emt.auth.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.security.service.userdetails.impl.UserDetailsServiceImpl;

public class JWTTokenFilter extends OncePerRequestFilter {// Run in each request, validate with TokenProvider

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String BEARER_TEXT = "Bearer ";

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = this.extractToken(request);
            if (token != null && jwtProvider.validateJWT(token)) {
                String username = jwtProvider.getUsernameFromJWT(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);

                if(auth.isAuthenticated()){
                    userService.saveLastLogin(username);
                }
            }
        } catch (Exception e) {
            log.error("fail in doFilterInternal", e);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String headerCleaned = request.getHeader("Authorization");

        if (headerCleaned != null && headerCleaned.startsWith(BEARER_TEXT)) {

            String substring = headerCleaned.substring(BEARER_TEXT.length());

            return substring;
        }
        return null;
    }
}
