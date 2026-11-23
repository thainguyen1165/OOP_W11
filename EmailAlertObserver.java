/*
Nguyen Chinh Thai
202419094
 */
final class EmailAlertObserver implements IAlertObserver {
    public void update(AlertEvent alert) {
        System.out.printf("[EMAIL SIMULATION] %s at %s%n", alert.severity(), alert.sensorName());
    }
}
