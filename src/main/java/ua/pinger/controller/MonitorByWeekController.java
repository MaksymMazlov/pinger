package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.domain.MonitoringByWeek;
import ua.pinger.service.MonitoringByWeekService;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring/week")
public class MonitorByWeekController
{
    @Autowired
    MonitoringByWeekService weekService;

    @GetMapping(value = "/{resourceId}")
    public List<MonitoringByWeek> getAll(Authentication authentication, @PathVariable int resourceId)
    {
        Account account = (Account) authentication.getPrincipal();
        return weekService.getAll7day(resourceId);
    }
}
