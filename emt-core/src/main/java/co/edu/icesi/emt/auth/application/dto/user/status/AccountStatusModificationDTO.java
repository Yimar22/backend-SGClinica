package co.edu.icesi.emt.auth.application.dto.user.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountStatusModificationDTO {

    private boolean isEnabled;

    @JsonCreator
    public AccountStatusModificationDTO(@JsonProperty("isEnabled") boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
