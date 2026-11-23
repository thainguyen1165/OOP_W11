/*
Nguyen Chinh Thai
202419094
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class SensorMonitor {
    private final List<IAlertObserver> observers = new ArrayList<>();

    void attach(IAlertObserver observer) {
        Objects.requireNonNull(observer);
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    void detach(IAlertObserver observer) {
        observers.remove(observer);
    }

    void checkSensor(String name, ISensor sensor) {
        double value = sensor.readValue();
        if (!(value >= 50)) {
            return;
        }

        var severity = value >= 70 ? Severity.CRITICAL : Severity.WARNING;
        var alert = new AlertEvent(name, value, sensor.getUnit(), severity, LocalDateTime.now());
        for (var observer : List.copyOf(observers)) {
            try {
                observer.update(alert);
            } catch (RuntimeException exception) {
                System.err.println("[OBSERVER ERROR] " + exception.getMessage());
            }
        }
    }
}
