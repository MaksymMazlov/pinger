package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.domain.MonitoringByWeek;
import ua.pinger.repository.MonitoringByWeekRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonitoringByWeekService
{
    @Autowired
    private MonitoringByWeekRepository weekRepository;

    public List<MonitoringByWeek> getAll7day(int resourceId)
    {
        return weekRepository.findAllByAccountResourceIdAndDateBetween(resourceId, LocalDate.now().minusWeeks(7), LocalDate.now());
    }
}
