/*
Nguyen Chinh Thai
202419094
 */
record EmergencyBridgeObserver(IEmergencyMediator mediator) implements IAlertObserver {
    public void update(AlertEvent alert) {
        if (alert.severity() == Severity.CRITICAL) {
            mediator.notifyFireDetected(alert.sensorName());
        }
    }
}
