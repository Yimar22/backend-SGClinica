package co.edu.icesi.emt.core.application.dto.personalinformation;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.application.dto.personalinformation.civilstatus.CivilStatusDTO;
import co.edu.icesi.emt.core.application.dto.personalinformation.gender.GenderDTO;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;

public class PersonalInformationRetrievalDTO {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Instant birthDate;
    private final GenderDTO gender;
    private final CivilStatusDTO civilStatus;
    private final String phoneNumber;
    private final String address;
    private final long age;

    @JsonCreator
    public PersonalInformationRetrievalDTO(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName, @JsonProperty("email") String email,
            @JsonProperty("birthDate") Instant birthDate,
            @JsonProperty("gender") GenderDTO gender, @JsonProperty("civilStatus") CivilStatusDTO civilStatus,
            @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("address") String address,
            @JsonProperty("age") long age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
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

    public GenderDTO getGender() {
        return gender;
    }

    public CivilStatusDTO getCivilStatus() {
        return civilStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public long getAge() {
        return age;
    }

    public static PersonalInformationRetrievalDTO from(PersonalInformation personalInformation) {
        return new PersonalInformationRetrievalDTO(personalInformation.getId(), personalInformation.getFirstName(),
                personalInformation.getLastName(), personalInformation.getEmail(), personalInformation.getBirthDate(),
                GenderDTO.from(personalInformation.getGender()),
                CivilStatusDTO.from(personalInformation.getCivilStatus()),
                personalInformation.getPhoneNumber(), personalInformation.getAddress(), personalInformation.getAge());
    }

    public static List<PersonalInformationRetrievalDTO> from(List<PersonalInformation> personalInformations) {
        return personalInformations.stream().map(PersonalInformationRetrievalDTO::from).toList();
    }
}
