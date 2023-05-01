package co.edu.icesi.emt.core.domain.model.disease;

public class Disease {

    // This code is specified by the CIE-10 document
    public final String code;
    public final String name;

    public Disease(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
