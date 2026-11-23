/*
Nguyen Chinh Thai
202419094
 */
final class DashboardObserver implements IAlertObserver {
    public void update(AlertEvent alert) {
        System.out.printf("[DASHBOARD] %s: %.1f%s [%s]%n",
                alert.sensorName(), alert.value(), alert.unit(), alert.severity());
    }
}
