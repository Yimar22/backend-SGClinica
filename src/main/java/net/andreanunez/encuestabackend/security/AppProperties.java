package net.andreanunez.encuestabackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//En esta clase vamos a estar leyendo las variables de entorno del archivo application.properties
@Component
public class AppProperties {

    @Autowired
    private Environment env;

    // para obtener la variable token del archivo application.properties
    public String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }
}
