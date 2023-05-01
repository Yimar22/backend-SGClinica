package co.edu.icesi.emt.common.exception.model;

public class RootAdminCanNotBeRemovedException extends Exception implements EMTException {

    private static final String DOMAIN_MSG = "El administrador raíz no puede ser eliminado";
    private static final String MSG = "Root admin can not be removed";

    public RootAdminCanNotBeRemovedException() {
        super(MSG);
    }

    @Override
    public String getDomainMessage() {
        return DOMAIN_MSG;
    }
}
