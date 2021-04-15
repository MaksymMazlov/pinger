package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.dto.RequestMonitorByDayUptime;
import ua.pinger.repository.MonitoringByDayRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MonitoringByDayService
{
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringByDayService.class);
    @Autowired
    private MonitoringByDayRepository monitoringByDayRepository;

    public RequestMonitorByDayUptime getUptimeForPercent(int accountID, int resourceId)
    {

        List<MonitoringByDay> allMonitorByDay = monitoringByDayRepository.findAllByAccountIdAndResourceId(accountID, resourceId);
        int countTrue = 0;
        for (MonitoringByDay elem : allMonitorByDay)
        {
            if (elem.isAvailable())
            {
                countTrue++;
            }
        }
        BigDecimal percent = BigDecimal.valueOf(100D * countTrue / allMonitorByDay.size());
        RequestMonitorByDayUptime dayUptime = new RequestMonitorByDayUptime();
        dayUptime.setUptime(percent.setScale(2, BigDecimal.ROUND_HALF_UP));
        LOG.info("UPTIME ------> : {} %", dayUptime.getUptime());
        return dayUptime;
    }
}
