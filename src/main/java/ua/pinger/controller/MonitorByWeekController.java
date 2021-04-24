package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.MonitoringByWeek;
import ua.pinger.service.AuthorizationService;
import ua.pinger.service.MonitoringByWeekService;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring/week")
public class MonitorByWeekController
{
    @Autowired
    MonitoringByWeekService weekService;
    @Autowired
    AuthorizationService authorizationService;

    @GetMapping(value = "/{resourceId}")
    public List<MonitoringByWeek> getAll(@PathVariable int resourceId)
    {
        return weekService.getAll7day(resourceId);
    }
}
