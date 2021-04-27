package ua.pinger.service.monitoring.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.repository.MonitoringByDayRepository;
import ua.pinger.repository.MonitoringEventRepository;
import ua.pinger.service.notifications.NotificationService;

@RunWith(MockitoJUnitRunner.class)
public class PingResourceTaskTest
{
    @Spy
    private EventFactory eventFactory = new EventFactory();
    @Mock
    private MonitoringByDayRepository byDayRepository;
    @Mock
    private MonitoringEventRepository eventRepository;
    @Mock
    private NotificationService notificationService;
    @Mock
    private AccountResource testResource;
    @InjectMocks()
    private PingResourceTask task;

    @Test
    public void testWhenPingSuccess()
    {
        Mockito.when(testResource.getId()).thenReturn(123);
        Mockito.when(testResource.getHost()).thenReturn("localhost");
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
    }

    @Test
    public void testWhenPingFailed()
    {
        Mockito.when(testResource.getId()).thenReturn(123);
        Mockito.when(testResource.getHost()).thenReturn("192.168.5.5");
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
    }
    @Test
    public void testWhenPingWasSuccessNowFailed()
    {
        MonitoringByDay oldStatus = new MonitoringByDay();
        oldStatus.setAvailable(true);
        Mockito.when(byDayRepository.findTopByResourceIdOrderByIdDesc(Mockito.anyInt())).thenReturn(oldStatus);

        Mockito.when(testResource.getId()).thenReturn(123);
        Mockito.when(testResource.getHost()).thenReturn("192.168.5.5");
        Mockito.when(testResource.getInterval()).thenReturn(5);
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
        Mockito.verify(eventRepository).save(Mockito.any());
        Mockito.verify(notificationService).sendMail(Mockito.any());
    }
}