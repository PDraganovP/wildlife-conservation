package wildlifeConservation.domain.entities.enums;

public enum ConservationStatus {
    EXTINCT("Extint"), THREATENED("Threatened"),
    LOWER_RISK("Lower risk"), NOT_FULLY_ASSESSED("Not fully assessed");

    private String status;

    ConservationStatus(String status) {
        this.status = status;
    }
}
