package co.edu.icesi.emt.common.exception.model;

public class UsernameAlreadyExistsException extends Exception implements EMTException {

    private static final String MSG = "This username already exists";
    private static final String DOMAIN_MSG = "Este nombre de usuario ya existe";

    public UsernameAlreadyExistsException() {
        super(MSG);
    }

    @Override
    public String getDomainMessage() {
        return DOMAIN_MSG;
    }
}
