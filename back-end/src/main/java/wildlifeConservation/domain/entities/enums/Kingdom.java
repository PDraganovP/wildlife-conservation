package wildlifeConservation.domain.entities.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Kingdom {
    ANIMAL("Animal"), PLANT("Plant"), FUNGI("Fungi"),
    MONERA("Monera"), PROTISTA("Protista");
    private String type;

    Kingdom(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
