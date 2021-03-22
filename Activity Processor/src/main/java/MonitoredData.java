import java.time.*;
import java.time.temporal.ChronoUnit;
public class MonitoredData {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected String activity;
    public MonitoredData(LocalDateTime startTime, LocalDateTime endTime, String activity){
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }
    public long getDuration() {
        return ChronoUnit.MINUTES.between(startTime, endTime);
    }
}
