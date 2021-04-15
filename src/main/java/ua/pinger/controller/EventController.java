package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.service.MonitoringEventService;

import java.util.List;

@RestController
public class EventController
{
    @Autowired
    MonitoringEventService eventsService;

    @GetMapping("/api/events/{resourceID}")
    public List<MonitoringEvents> getEvents(Authentication authentication,
                                            @PathVariable int resourceID)
    {

        Account account = (Account) authentication.getPrincipal();
        return eventsService.findAllEvents(resourceID, account.getId());
    }
}
