package wildlifeConservation.domain.entities.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ConservationStatus {
    EXTINCT("Extinct"), THREATENED("Threatened"),
    LOWER_RISK("Lower risk"), NOT_FULLY_ASSESSED("Not fully assessed");

    private String status;

    ConservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
