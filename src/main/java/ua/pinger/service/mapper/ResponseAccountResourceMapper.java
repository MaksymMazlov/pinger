package ua.pinger.service.mapper;

import org.springframework.stereotype.Component;
import ua.pinger.domain.AccountResource;
import ua.pinger.dto.ResponseAccountResourceDto;

import java.time.format.DateTimeFormatter;

@Component
public class ResponseAccountResourceMapper
{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd в HH:mm:ss");

    public ResponseAccountResourceDto toDto(AccountResource accountResource)
    {
        if (accountResource == null)
        {
            return null;
        }
        ResponseAccountResourceDto responseResource = new ResponseAccountResourceDto();
        responseResource.setId(accountResource.getId());
        responseResource.setName(accountResource.getName());
        responseResource.setStatus(accountResource.getStatus());
        responseResource.setType(accountResource.getType());
        responseResource.setHost(accountResource.getHost());
        responseResource.setMonitoringInterval(accountResource.getInterval());
        responseResource.setCreated(FORMATTER.format(accountResource.getCreated().toLocalDateTime()));
        responseResource.setAccountId(accountResource.getAccountId());
        responseResource.setSmsNotification(accountResource.isSmsNotification());
        return responseResource;
    }
}
