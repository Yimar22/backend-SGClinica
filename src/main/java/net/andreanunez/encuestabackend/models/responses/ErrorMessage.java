package net.andreanunez.encuestabackend.models.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    // timestamp de cuando se generó el error
    private Date timestamp;
    private String message;
    private int status;

}
