/*
Nguyen Chinh Thai
202419094
 */
import java.time.LocalDateTime;

record AlertEvent(String sensorName, double value, String unit,
                  Severity severity, LocalDateTime occurredAt) {}
