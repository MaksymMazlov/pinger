package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.service.AuthorizationService;
import ua.pinger.service.MonitoringEventService;

import java.util.List;

@RestController
public class EventController
{
    @Autowired
    MonitoringEventService eventsService;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/api/events/{resourceID}")
    public List<MonitoringEvents> getEvents(@PathVariable int resourceID)
    {
        Account account = authorizationService.currentAccount();
        return eventsService.findAllEvents(resourceID, account.getId());
    }
}
