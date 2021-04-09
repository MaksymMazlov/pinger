package ua.pinger.service.monitoring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.domain.MonitoringByWeek;
import ua.pinger.repository.MonitoringByDayRepository;
import ua.pinger.repository.MonitoringByWeekRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
@Scope("prototype")
public class MonitoringDayToWeekTask extends AbstractTask
{
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringDayToWeekTask.class);

    @Autowired
    MonitoringByDayRepository dayRepository;
    @Autowired
    MonitoringByWeekRepository weekRepository;

    @Override
    protected void runImpl()
    {
        LOG.info("MonitoringDayToWeekTask -> ");
        List<MonitoringByDay> listMonitorsDay = dayRepository.findAll();
        Map<Integer, List<MonitoringByDay>> map = groupByResourceId(listMonitorsDay);
        Set<Integer> resourceIds = map.keySet();
        for (int resourceId : resourceIds)
        {
            boolean available = true;
            List<MonitoringByDay> list = map.get(resourceId);

            for (MonitoringByDay row : list)
            {
                if (!row.isAvailable())
                {
                    available = false;
                    break;
                }
            }

            MonitoringByWeek monitoringByWeek = new MonitoringByWeek();
            monitoringByWeek.setDate(Date.valueOf(LocalDate.now()));
            monitoringByWeek.setAvailable(available);
            monitoringByWeek.setAccountResourceId(resourceId);
            weekRepository.save(monitoringByWeek);
        }
    }

    private Map<Integer, List<MonitoringByDay>> groupByResourceId(List<MonitoringByDay> listMonitorsDay)
    {
        Map<Integer, List<MonitoringByDay>> map = new HashMap<>();

        for (MonitoringByDay dayElem : listMonitorsDay)
        {
            int resourceId = dayElem.getResourceId();
            if (map.containsKey(resourceId))
            {
                map.get(resourceId).add(dayElem);
            }
            else
            {
                ArrayList<MonitoringByDay> list = new ArrayList<>();
                list.add(dayElem);
                map.put(resourceId, list);
            }
        }
        return map;
    }
}
