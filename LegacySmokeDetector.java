/*
Nguyen Chinh Thai
202419094
 */
final class LegacySmokeDetector {
    private int smokeLevel;

    LegacySmokeDetector(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    int getSmokeLevel() {
        return smokeLevel;
    }

    String getDescription() {
        return "Legacy smoke detector";
    }

    void setSmokeLevel(int value) {
        smokeLevel = Math.max(0, Math.min(1000, value));
    }
}
