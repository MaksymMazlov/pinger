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
import ua.pinger.service.notifications.NotificationService;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Scope("prototype")
public class PingResourceTask extends AbstractTask
{
    private static final Logger LOG = LoggerFactory.getLogger(PingResourceTask.class);

    private AccountResource resource;
    @Autowired
    private MonitoringByDayRepository byDayRepository;
    @Autowired
    private MonitoringEventRepository eventRepository;
    @Autowired
    private NotificationService notificationService;

    public PingResourceTask(AccountResource resource)
    {
        this.resource = resource;
    }

    @Override
    public void runImpl()
    {
        MonitoringResult monitoringResult = new MonitoringResult();
        InetAddress geek = null;
        try
        {
            geek = InetAddress.getByName(resource.getHost());
            if (geek.isReachable(5000))
            {
                monitoringResult.setAvailable(true);
                monitoringResult.setReason("Ping: OK");
                LOG.info("IN run - PING: available TRUE for host : {}!", resource.getHost());
            }
            else
            {
                monitoringResult.setAvailable(false);
                monitoringResult.setReason("TIMEOUT");
                LOG.info("IN run - PING > 5000 timeout: available FALSE for host : {}!", resource.getHost());
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

                notificationService.sendMail(event);

                if (resource.isSmsNotification())
                {
                    notificationService.sendSms(event);
                }
            }
        }
    }
}
