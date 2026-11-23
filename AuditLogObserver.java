/*
Nguyen Chinh Thai
202419094
 */
final class AuditLogObserver implements IAlertObserver {
    public void update(AlertEvent alert) {
        System.out.printf("[AUDIT] %s | %s | %s%n",
                alert.occurredAt(), alert.sensorName(), alert.severity());
    }
}
