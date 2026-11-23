/*
Nguyen Chinh Thai
202419094
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var legacy = new LegacySmokeDetector(735);
        ISensor sensor = new SmokeSensorAdapter(legacy);
        if (sensor.readValue() != 73.5 || !"% smoke".equals(sensor.getUnit())) {
            throw new AssertionError("Expected 73.5% smoke from the adapter");
        }

        var alarm = new Alarm();
        var door = new DoorController();
        var ventilation = new VentilationController();
        var monitor = new SensorMonitor();
        var email = new EmailAlertObserver();
        var received = new ArrayList<Severity>();
        var firstOnly = new ArrayList<Severity>();

        monitor.attach(new IAlertObserver() {
            public void update(AlertEvent alert) {
                firstOnly.add(alert.severity());
                monitor.detach(this);
                throw new IllegalStateException("Simulated failure for testing");
            }
        });
        IAlertObserver recorder = alert -> received.add(alert.severity());
        monitor.attach(recorder);
        monitor.attach(recorder);
        monitor.attach(new DashboardObserver());
        monitor.attach(email);
        monitor.attach(new AuditLogObserver());
        monitor.attach(new EmergencyBridgeObserver(new EmergencyMediator(alarm, door, ventilation)));

        for (int level : new int[]{499, 500, 699, 700}) {
            legacy.setSmokeLevel(level);
            System.out.printf("%n[READING] %.1f%s%n", sensor.readValue(), sensor.getUnit());
            int previousCount = received.size();
            monitor.checkSensor("Lab W11", sensor);

            int expectedCount = level < 500 ? 0 : 1;
            if (received.size() - previousCount != expectedCount) {
                throw new AssertionError("Unexpected number of alerts at level " + level);
            }

            boolean critical = level >= 700;
            if (alarm.isActive() != critical || door.isUnlocked() != critical
                    || ventilation.isStopped() != critical) {
                throw new AssertionError("Unexpected device state at level " + level);
            }
        }
        if (!received.equals(List.of(Severity.WARNING, Severity.WARNING, Severity.CRITICAL))
                || !firstOnly.equals(List.of(Severity.WARNING))) {
            throw new AssertionError("Unexpected alerts: " + received + ", first observer: " + firstOnly);
        }

        monitor.detach(email);
        monitor.detach(recorder);
        legacy.setSmokeLevel(735);
        monitor.checkSensor("Lab W11", sensor);
        if (received.size() != 3) {
            throw new AssertionError("Detached observer received an alert");
        }
        System.out.println("All tests passed.");
    }
}
