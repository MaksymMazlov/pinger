package ua.pinger.service.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.repository.AccountResourceRepository;
import ua.pinger.service.monitoring.task.HttpResourceTask;
import ua.pinger.service.monitoring.task.MonitoringDayToWeekTask;
import ua.pinger.service.monitoring.task.PingResourceTask;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class MonitoringService
{
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringService.class);
    @Autowired
    private AccountResourceRepository resourceRepository;
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
    @Autowired
    private ApplicationContext context;
    private Map<Integer, ScheduledFuture> futureMap = new HashMap<>();

    @PostConstruct
    public void startAllTasks()
    {
        List<AccountResource> resourceList = resourceRepository.findAll();
        LOG.info("IN startAllTasks - find {} resources", resourceList.size());
        for (AccountResource elem : resourceList)
        {
            enqueue(elem);
        }
        startMonitoringDayToWeek();
    }

    private void startMonitoringDayToWeek()
    {
        MonitoringDayToWeekTask dayToWeekTask = context.getBean(MonitoringDayToWeekTask.class);
        service.schedule(dayToWeekTask, 0, TimeUnit.HOURS);
    }

    public void cancel(int resourceId)
    {
        if (futureMap.containsKey(resourceId))
        {
            ScheduledFuture sf = futureMap.get(resourceId);
            sf.cancel(true);
            LOG.info("IN CANCEL task for resource id : {}", resourceId);
        }
    }

    public void enqueue(AccountResource elem)
    {

        if (elem.getType().equals(MonitoringType.URL.name()))
        {
            HttpResourceTask httpTask = context.getBean(HttpResourceTask.class, elem);
            ScheduledFuture scheduledFuture = service.scheduleAtFixedRate(httpTask, 0, elem.getInterval(), TimeUnit.MINUTES);
            futureMap.put(elem.getId(), scheduledFuture);
            LOG.info("IN startAllTasks - start httpTask for: {}", elem.getName());
        }
        else if (elem.getType().equals(MonitoringType.PING.name()))
        {
            PingResourceTask pingTask = context.getBean(PingResourceTask.class, elem);
            ScheduledFuture scheduledFuture = service.scheduleAtFixedRate(pingTask, 0, elem.getInterval(), TimeUnit.MINUTES);
            futureMap.put(elem.getId(), scheduledFuture);
            LOG.info("IN startAllTasks - start pingTask for: {}", elem.getName());
        }
    }
}
