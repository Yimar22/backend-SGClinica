package co.edu.icesi.emt.core.domain.model.patient.diseasehistorial;

import co.edu.icesi.emt.core.domain.model.disease.Disease;

public class DiseaseHistorial {

    private Disease firstDisease;
    private Disease secondDisease;
    private Disease thirdDisease;
    private Disease fourthDisease;

    public DiseaseHistorial(Disease firstDisease, Disease secondDisease, Disease thirdDisease,
            Disease fourthDisease) {
        this.firstDisease = firstDisease;
        this.secondDisease = secondDisease;
        this.thirdDisease = thirdDisease;
        this.fourthDisease = fourthDisease;
    }

    public Disease getFirstDisease() {
        return firstDisease;
    }

    public void setFirstDisease(Disease firstDisease) {
        this.firstDisease = firstDisease;
    }

    public Disease getSecondDisease() {
        return secondDisease;
    }

    public void setSecondDisease(Disease secondDisease) {
        this.secondDisease = secondDisease;
    }

    public Disease getThirdDisease() {
        return thirdDisease;
    }

    public void setThirdDisease(Disease thirdDisease) {
        this.thirdDisease = thirdDisease;
    }

    public Disease getFourthDisease() {
        return fourthDisease;
    }

    public void setFourthDisease(Disease fourthDisease) {
        this.fourthDisease = fourthDisease;
    }
}
