package net.andreanunez.encuestabackend.models.responses;

import lombok.Data;

/**
 * Esta clase va a contener sólo los campos que queremos retornar de la clase
 * UserEntity
 */
@Data
public class UserRest {

    private long id;

    private String name;

    private String email;
}
