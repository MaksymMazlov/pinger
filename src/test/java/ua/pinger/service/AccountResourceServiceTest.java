package ua.pinger.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.dto.ResponseAccountResourceDto;
import ua.pinger.repository.AccountResourceRepository;
import ua.pinger.service.mapper.ResponseAccountResourceMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountResourceServiceTest
{
    @Mock
    private AccountResourceRepository resourceRepository;
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
}