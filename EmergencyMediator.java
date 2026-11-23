/*
Nguyen Chinh Thai
202419094
 */
record EmergencyMediator(Alarm alarm, DoorController door, VentilationController ventilation)
        implements IEmergencyMediator {
    public void notifyFireDetected(String sensorName) {
        alarm.activate();
        door.unlock();
        ventilation.stop();
        System.out.printf("[FIRE_DETECTED] %s | Alarm on, door unlocked, ventilation stopped%n", sensorName);
    }
}
