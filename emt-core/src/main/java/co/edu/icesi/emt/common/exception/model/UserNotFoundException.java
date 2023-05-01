package co.edu.icesi.emt.common.exception.model;

public class UserNotFoundException extends Exception implements EMTException {

    private static final String MSG = "User not found";
    private static final String DOMAIN_MSG = "Usuario no encontrado";

    public UserNotFoundException() {
        super(MSG);
    }

    @Override
    public String getDomainMessage() {
        return DOMAIN_MSG;
    }
}