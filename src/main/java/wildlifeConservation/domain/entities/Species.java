package wildlifeConservation.domain.entities;

import wildlifeConservation.domain.entities.enums.ConservationStatus;
import wildlifeConservation.domain.entities.enums.Kingdom;

import javax.persistence.*;

@Entity
@Table(name = "species")
public class Species extends BaseEntity {
    private String name;
    private String habitat;
    private ConservationStatus conservationStatus;
    private Kingdom kingdom;

    public Species() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "habitat")
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "conservation_status")
    public ConservationStatus getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "kingdom")
    public Kingdom getKingdom() {
        return this.kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }
}
