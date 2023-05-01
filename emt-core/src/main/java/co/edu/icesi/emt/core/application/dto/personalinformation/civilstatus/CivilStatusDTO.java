package co.edu.icesi.emt.core.application.dto.personalinformation.civilstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.personalinformation.CivilStatus;

public class CivilStatusDTO {

    private final int id;
    private final String name;

    @JsonCreator
    public CivilStatusDTO(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CivilStatusDTO from(CivilStatus civilStatus) {
        return new CivilStatusDTO(civilStatus.getId(), civilStatus.getName());
    }

    public static CivilStatus fromDTO(CivilStatusDTO civilStatus) {
        return CivilStatus.findById(civilStatus.getId());
    }
}
