package co.edu.icesi.emt.core.application.dto.personalinformation;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformationPreview;

public class PersonalInformationPreviewDTO {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;

    @JsonCreator
    public PersonalInformationPreviewDTO(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName, @JsonProperty("email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public static PersonalInformationPreviewDTO from(
            PersonalInformationPreview personalInformationPreview) {
        return new PersonalInformationPreviewDTO(personalInformationPreview.getId(),
                personalInformationPreview.getFirstName(), personalInformationPreview.getLastName(),
                personalInformationPreview.getEmail());
    }

    public static List<PersonalInformationPreviewDTO> from(
            List<PersonalInformationPreview> personalInformationPreviews) {
        return personalInformationPreviews.stream()
                .map(PersonalInformationPreviewDTO::from)
                .collect(Collectors.toList());
    }
}
