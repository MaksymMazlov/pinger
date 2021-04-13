package ua.pinger.service.monitoring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.domain.enumeration.EventType;
import ua.pinger.repository.MonitoringByDayRepository;
import ua.pinger.repository.MonitoringEventRepository;
import ua.pinger.service.monitoring.MonitoringResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Scope("prototype")
public class HttpResourceTask extends AbstractTask
{
    private static final Logger LOG = LoggerFactory.getLogger(HttpResourceTask.class);
    private AccountResource resource;

    @Autowired
    private MonitoringByDayRepository byDayRepository;
    @Autowired
    private MonitoringEventRepository eventRepository;

    public HttpResourceTask(AccountResource resource)
    {
        this.resource = resource;
    }

    @Override
    public void runImpl()
    {
        MonitoringResult monitoringResult = new MonitoringResult();
        HttpURLConnection connection = null;
        try
        {
            connection = (HttpURLConnection) new URL(resource.getHost()).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200)
            {
                monitoringResult.setAvailable(false);
                monitoringResult.setReason("HTTP: " + connection.getResponseMessage());
                LOG.info("IN run - RESPONSE CODE: {} available FALSE for URL: {}", responseCode, resource.getHost());
            }
            else
            {
                monitoringResult.setAvailable(true);
                monitoringResult.setReason("HTTP: " + connection.getResponseMessage());
                LOG.info("IN run - RESPONSE CODE: {} available TRUE for URL: {}", responseCode, resource.getHost());
            }
        }
        catch (IOException e)
        {
            LOG.error(e.getMessage(), e);
        }

        createEvent(monitoringResult);

        MonitoringByDay monitoringByDay = new MonitoringByDay();
        monitoringByDay.setStatus(monitoringResult.getReason());
        monitoringByDay.setDate(Date.valueOf(LocalDate.now()));
        monitoringByDay.setTime(Time.valueOf(LocalTime.now()));
        monitoringByDay.setAvailable(monitoringResult.isAvailable());
        monitoringByDay.setResourceId(resource.getId());
        byDayRepository.save(monitoringByDay);
    }

    private void createEvent(MonitoringResult monitoringResult)
    {
        MonitoringByDay lastMonitoringByDay = byDayRepository.findTopByResourceIdOrderByIdDesc(resource.getId());
        if (lastMonitoringByDay != null)
        {
            if (monitoringResult.isAvailable() != lastMonitoringByDay.isAvailable())
            {
                MonitoringEvents event = new MonitoringEvents();
                event.setType(monitoringResult.isAvailable() ? EventType.UP : EventType.DOWN);
                event.setReason(monitoringResult.getReason());
                event.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
                event.setDuration(event.getDuration() + resource.getInterval());
                event.setAccountResourceId(resource.getId());
                eventRepository.save(event);
                LOG.info("--------------- EVENT PING ADD: {}, DURATION: {}", monitoringResult.isAvailable() ? "UP" : "DOWN", event.getDuration());
            }
        }
    }
}
