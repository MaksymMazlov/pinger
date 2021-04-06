package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.repository.MonitoringByDayRepository;

@Service
public class MonitoringByDayService
{
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringByDayService.class);
    @Autowired
    private MonitoringByDayRepository monitoringByDayRepository;

/*    public List<MonitoringByDay> getAllMonitoring(int userId)
    {
        только для авторизованого и его ресурсов
      return monitoringByDayRepository.find();
    }*/

}
