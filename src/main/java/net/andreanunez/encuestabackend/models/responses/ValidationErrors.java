package net.andreanunez.encuestabackend.models.responses;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase me sirve para devolver los errores de validación que se generan a
 * la hora de validar el modelo
 * La utiliza el método "handleValidationErrorException" de la clase
 * AppExceptionHandler (exceptions)
 */
@Data
@AllArgsConstructor
public class ValidationErrors {

    private Map<String, String> errors;

    // fecha en la que se generó el error
    private Date timestamp;

}
