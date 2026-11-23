/*
Nguyen Chinh Thai
202419094
 */
import java.util.Objects;

record SmokeSensorAdapter(LegacySmokeDetector legacy) implements ISensor {
    SmokeSensorAdapter {
        Objects.requireNonNull(legacy, "legacy");
    }

    public double readValue() {
        return legacy.getSmokeLevel() / 10.0;
    }

    public String getUnit() {
        return "% smoke";
    }
}
