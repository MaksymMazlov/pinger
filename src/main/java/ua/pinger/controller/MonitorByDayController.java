package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.service.MonitoringByDayService;

@RestController
@RequestMapping("/api/monitoring/day")
public class MonitorByDayController
{
    @Autowired
    MonitoringByDayService monitoringByDayService;

/*    @GetMapping
    public List<MonitoringByDay> getAllMonitoringByDay(Authentication authentication)
    {
        Account account = (Account) authentication.getPrincipal();
        return monitoringByDayService.getAllMonitoring(account.getId());
    }*/
}
