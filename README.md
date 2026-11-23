# Campus Safety Hub

## Thiết kế

- `ISensor` định nghĩa cách đọc giá trị và đơn vị. `LegacySmokeDetector` lưu mức khói theo thang 0–1000. `SmokeSensorAdapter` giữ tham chiếu đến đối tượng cũ và chia giá trị đọc cho 10 để trả về `% smoke`.
- `AlertEvent` là record bất biến chứa tên cảm biến, giá trị, đơn vị, mức cảnh báo và thời điểm.
- `IAlertObserver` định nghĩa cách nhận cảnh báo. `SensorMonitor` chỉ giao tiếp qua interface này, quản lý đăng ký và quyết định ngưỡng. Dưới 50% là bình thường, từ 50% đến dưới 70% là `WARNING`, và từ 70% là `CRITICAL`.
- `DashboardObserver`, `EmailAlertObserver` và `AuditLogObserver` lần lượt hiển thị cảnh báo, mô phỏng email và ghi nhật ký trên console.
- `EmergencyBridgeObserver` chuyển `CRITICAL` thành `FIRE_DETECTED` qua `IEmergencyMediator`. `EmergencyMediator` bật còi, mở khóa cửa và dừng thông gió mô phỏng. Ba thiết bị độc lập với nhau.
