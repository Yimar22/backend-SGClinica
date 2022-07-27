package net.andreanunez.encuestabackend.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.andreanunez.encuestabackend.annotations.ValueOfEnum;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {

    // lista con todos los valores que puede tener el enum
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        // vamos a mapear los valores del enum para compararlos con el string que vamos
        // a recibir por parámetro
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true; // la validación falla
        }
        return acceptedValues.contains(value.toString());
    }

}
