package co.edu.icesi.emt.core.domain.model.personalinformation;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class PersonalInformation {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Instant birthDate;
    private final Gender gender;
    private final CivilStatus civilStatus;
    private final String phoneNumber;
    private final String address;
    private final long age;

    public PersonalInformation(String id, String firstName, String lastName, String email, Instant birthDate,
            Gender gender, CivilStatus civilStatus, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.phoneNumber = phoneNumber;
        this.address = address;

        Instant now = Instant.now();
        Instant ago = Instant.ofEpochSecond(birthDate.getEpochSecond());

        this.age = ChronoUnit.YEARS.between(
                ago.atZone(ZoneId.systemDefault()),
                now.atZone(ZoneId.systemDefault()));
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

    public Gender getGender() {
        return gender;
    }

    public CivilStatus getCivilStatus() {
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
}
