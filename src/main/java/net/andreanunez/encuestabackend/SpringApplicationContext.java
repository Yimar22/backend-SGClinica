package net.andreanunez.encuestabackend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Esta clase sirve para poder obtener los beans que se han creado de forma
 * global, en el main
 * Ejemplo: la utiliza SecurityConstants para obtener el bean de AppProperties
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        CONTEXT = applicationContext;

    }

    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

}
