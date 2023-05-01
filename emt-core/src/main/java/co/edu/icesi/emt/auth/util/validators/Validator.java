package co.edu.icesi.emt.auth.util.validators;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    void validate(final HttpServletRequest httpRequest) throws Exception;
}
