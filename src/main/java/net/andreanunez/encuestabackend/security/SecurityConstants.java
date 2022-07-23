package net.andreanunez.encuestabackend.security;

import net.andreanunez.encuestabackend.SpringApplicationContext;

/**
 * En esta clase vamos a tener todas las constantes que vamos a usar dentro del
 * paquete de security
 */
public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    // tiempo de expiración del token
    public static final long EXPIRATION_DATE = 864000000; // 10 días en ms

    public static final String LOGIN_URL = "/users/login";

    // Este método sirve para obtener el token secret de la clase AppProperties
    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");

        return appProperties.getTokenSecret();
    }

}
