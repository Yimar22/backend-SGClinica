package co.edu.icesi.emt.common.exception.model;

public class UserIsNotAdminException extends Exception implements EMTException {

    private static final String MSG = "This user is not administrator";
    private static final String DOMAIN_MSG = "Este usuario no es administrador";

    public UserIsNotAdminException() {
        super(MSG);
    }

    @Override
    public String getDomainMessage() {
        return DOMAIN_MSG;
    }
}