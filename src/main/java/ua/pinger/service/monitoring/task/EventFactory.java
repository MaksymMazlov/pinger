package ua.pinger.service.monitoring.task;

import org.springframework.stereotype.Component;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.domain.enumeration.EventType;
import ua.pinger.service.monitoring.MonitoringResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class EventFactory {

    public MonitoringEvents createEvent(AccountResource resource, MonitoringResult monitoringResult) {
        MonitoringEvents event = new MonitoringEvents();
        event.setType(monitoringResult.isAvailable() ? EventType.UP : EventType.DOWN);
        event.setReason(monitoringResult.getReason());
        event.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
        event.setDuration(event.getDuration() + resource.getInterval());
        event.setAccountResourceId(resource.getId());
        event.setAccountResource(resource);
        return event;
    }
}
