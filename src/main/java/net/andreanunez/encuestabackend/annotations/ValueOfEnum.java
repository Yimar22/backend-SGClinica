package net.andreanunez.encuestabackend.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.andreanunez.encuestabackend.validators.ValueOfEnumValidator;

@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueOfEnum {

    // enum que vamos a recibir por parametro
    Class<? extends Enum<?>> enumClass();

    String message() default "{encuesta.constraints.enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
