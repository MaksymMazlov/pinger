package ua.pinger.service.monitoring.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringByDay;
import ua.pinger.repository.MonitoringByDayRepository;
import ua.pinger.repository.MonitoringEventRepository;
import ua.pinger.service.notifications.NotificationService;


@RunWith(MockitoJUnitRunner.class)
public class HttpResourceTaskTest
{
    @Mock
    private MonitoringByDayRepository byDayRepository;
    @Mock
    private MonitoringEventRepository eventRepository;
    @Mock
    private NotificationService notificationService;
    @Mock
    private AccountResource testResource;
    @InjectMocks()
    private HttpResourceTask task;

    @Test
    public void testWhenHtmlSuccess()
    {
        Mockito.when(testResource.getId()).thenReturn(1);
        Mockito.when(testResource.getHost()).thenReturn("http://google.com");
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
    }
    @Test
    public void testWhenHtmlFailed()
    {
        Mockito.when(testResource.getId()).thenReturn(1);
        Mockito.when(testResource.getHost()).thenReturn("https://httpstat.us/504");
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
    }
    @Test
    public void testWhenHtmlWasSuccessNowFailed()
    {
        MonitoringByDay oldStatus = new MonitoringByDay();
        oldStatus.setAvailable(true);
        Mockito.when(byDayRepository.findTopByResourceIdOrderByIdDesc(Mockito.anyInt())).thenReturn(oldStatus);

        Mockito.when(testResource.getId()).thenReturn(123);
        Mockito.when(testResource.getHost()).thenReturn("https://httpstat.us/504");
        Mockito.when(testResource.getInterval()).thenReturn(5);
        Mockito.when(testResource.isSmsNotification()).thenReturn(true);
        task.runImpl();
        Mockito.verify(byDayRepository).save(Mockito.any());
        Mockito.verify(eventRepository).save(Mockito.any());
        Mockito.verify(notificationService).sendMail(Mockito.any());
        Mockito.verify(notificationService).sendSms(Mockito.any());
    }
}