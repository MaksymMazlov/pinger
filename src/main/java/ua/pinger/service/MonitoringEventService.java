package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.repository.MonitoringEventRepository;

import java.util.List;

@Service
public class MonitoringEventService
{
    @Autowired
    MonitoringEventRepository eventRepository;

    public List<MonitoringEvents> findAllEvents(int resourceID, int accountId)
    {

        return eventRepository.findAllByAccountResourceId(resourceID, accountId);
    }

}
