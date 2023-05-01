package co.edu.icesi.emt.auth.application.dto.user;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.auth.domain.model.user.User;
import co.edu.icesi.emt.common.accountstatus.AccountStatus;

public class UserBasicRetrievalDTO {

    private final String username;
    private final String fullName;
    private final String accountStatus;
    private final Instant last_login;

    @JsonCreator
    public UserBasicRetrievalDTO(@JsonProperty("username") String username, @JsonProperty("fullName") String fullName,
            @JsonProperty("accountStatus") String accountStatus, @JsonProperty("last_login") Instant last_login) {
        this.username = username;
        this.fullName = fullName;
        this.accountStatus = accountStatus;
        this.last_login = last_login;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public Instant getLast_login() {
        return last_login;
    }

    public static UserBasicRetrievalDTO from(User user, String fullName) {
        return new UserBasicRetrievalDTO(user.getUsername(), fullName,
                user.isEnabled() ? AccountStatus.ACTIVE.getStatus() : AccountStatus.INACTIVE.getStatus(),
                user.getLastLogin());
    }

    public static List<UserBasicRetrievalDTO> from(List<User> users, Map<String, String> fullNames) {
        List<UserBasicRetrievalDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(from(user, fullNames.get(user.getUsername())));
        }
        return userDTOs;
    }
}
