package ua.pinger.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.domain.enumeration.ResourceStatus;
import ua.pinger.dto.RequestChangeStatusDto;
import ua.pinger.dto.ResponseAccountResourceDto;
import ua.pinger.repository.AccountResourceRepository;
import ua.pinger.service.mapper.ResponseAccountResourceMapper;
import ua.pinger.service.monitoring.MonitoringService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountResourceServiceTest
{
    @Mock
    private AccountResourceRepository resourceRepository;
    @Mock
    private MonitoringService monitoringService;
    @Spy
    private ResponseAccountResourceMapper accountResourceMapper = new ResponseAccountResourceMapper();
    @InjectMocks
    private AccountResourceService resourceService;

    @Test
    public void getResource()
    {
        AccountResource accountResource = new AccountResource();
        accountResource.setId(22);
        accountResource.setName("Turba");
        accountResource.setHost("turbosms.ua");
        accountResource.setType(MonitoringType.PING);
        accountResource.setInterval(10);
        accountResource.setCreated(Timestamp.valueOf(LocalDateTime.of(2021, 4, 24, 21, 28, 5)));
        accountResource.setAccountId(2);

        Mockito.when(resourceRepository.findByAccountIdAndId(2, 22)).thenReturn(accountResource);

        ResponseAccountResourceDto actual = resourceService.getResource(2, 22);
        assertEquals(22, actual.getId());
        assertEquals("Turba", actual.getName());
        assertEquals("turbosms.ua", actual.getHost());
        assertEquals(MonitoringType.PING, actual.getType());
        assertEquals(10, actual.getMonitoringInterval());
        assertEquals(2, actual.getAccountId());
        assertEquals("2021-04-24 в 21:28:05", actual.getCreated());
    }

    @Test
    public void changeStatusActiveToArchive()
    {
        RequestChangeStatusDto changeStatusDto = new RequestChangeStatusDto();
        changeStatusDto.setStatus(ResourceStatus.ARCHIVED);

        AccountResource oldAccountResource = new AccountResource();
        oldAccountResource.setStatus(ResourceStatus.ACTIVE);

        Mockito.when(resourceRepository.findByAccountIdAndId(2, 2)).thenReturn(oldAccountResource);
        Mockito.when(resourceRepository.save(oldAccountResource)).thenReturn(oldAccountResource);
        AccountResource accountResource = resourceService.changeStatus(2, changeStatusDto, 2);
        Mockito.verify(monitoringService).cancel(2);
        Assert.assertEquals(ResourceStatus.ARCHIVED,accountResource.getStatus());
    }

    @Test
    public void changeStatusArchiveToActive()
    {
        RequestChangeStatusDto changeStatusDto = new RequestChangeStatusDto();
        changeStatusDto.setStatus(ResourceStatus.ACTIVE);

        AccountResource oldAccountResource = new AccountResource();
        oldAccountResource.setStatus(ResourceStatus.ARCHIVED);

        Mockito.when(resourceRepository.findByAccountIdAndId(2, 2)).thenReturn(oldAccountResource);
        Mockito.when(resourceRepository.save(oldAccountResource)).thenReturn(oldAccountResource);
        AccountResource accountResource = resourceService.changeStatus(2, changeStatusDto, 2);
        Mockito.verify(monitoringService).enqueue(oldAccountResource);
        Assert.assertEquals(ResourceStatus.ACTIVE,accountResource.getStatus());
    }
}