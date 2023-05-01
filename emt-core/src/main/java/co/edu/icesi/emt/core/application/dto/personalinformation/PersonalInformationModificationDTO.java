package co.edu.icesi.emt.core.application.dto.personalinformation;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.personalinformation.CivilStatus;
import co.edu.icesi.emt.core.domain.model.personalinformation.Gender;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;

public class PersonalInformationModificationDTO {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Instant birthDate;
    private final int genderId;
    private final int civilStatusId;
    private final String phoneNumber;
    private final String address;

    @JsonCreator
    public PersonalInformationModificationDTO(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName, @JsonProperty("email") String email,
            @JsonProperty("birthDate") Instant birthDate,
            @JsonProperty("genderId") int genderId, @JsonProperty("civilStatusId") int civilStatusId,
            @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("address") String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.genderId = genderId;
        this.civilStatusId = civilStatusId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public int getGenderId() {
        return genderId;
    }

    public int getCivilStatusId() {
        return civilStatusId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public static PersonalInformation fromDTO(PersonalInformationModificationDTO dto) {
        return new PersonalInformation(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.getBirthDate(), Gender.findById(dto.getGenderId()), CivilStatus.findById(dto.getCivilStatusId()),
                dto.getPhoneNumber(), dto.getAddress());
    }
}
