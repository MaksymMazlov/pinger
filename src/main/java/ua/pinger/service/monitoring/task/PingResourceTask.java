package ua.pinger.service.monitoring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.repository.MonitoringByDayRepository;
import ua.pinger.service.monitoring.MonitoringResult;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Scope("prototype")
public class PingResourceTask extends AbstractTask
{
    private static final Logger LOG = LoggerFactory.getLogger(PingResourceTask.class);

    private AccountResource resource;
    @Autowired
    private MonitoringByDayRepository byDayRepository;

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

        MonitoringByDay monitoringByDay = new MonitoringByDay();
        monitoringByDay.setStatus(monitoringResult.getReason());
        monitoringByDay.setDate(Date.valueOf(LocalDate.now()));
        monitoringByDay.setTime(Time.valueOf(LocalTime.now()));
        monitoringByDay.setResourceId(resource.getId());

        byDayRepository.save(monitoringByDay);

    }
}
