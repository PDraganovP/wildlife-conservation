package wildlifeConservation.domain.models.view;

import wildlifeConservation.domain.entities.enums.ConservationStatus;
import wildlifeConservation.domain.entities.enums.Kingdom;

public class SpeciesViewModel {
    private String id;
    private String name;
    private String habitat;
    private ConservationStatus conservationStatus;
    private Kingdom kingdom;

    public SpeciesViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    public ConservationStatus getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }
}
