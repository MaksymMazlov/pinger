package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.domain.enumeration.ResourceStatus;
import ua.pinger.dto.RequestChangeStatusDto;
import ua.pinger.dto.RequestCreateOrUpdateResourceDto;
import ua.pinger.dto.ResponseAccountResourceDto;
import ua.pinger.exception.RestApiException;
import ua.pinger.repository.AccountResourceRepository;
import ua.pinger.service.mapper.ResponseAccountResourceMapper;
import ua.pinger.service.monitoring.MonitoringService;
import ua.pinger.service.validator.HostValidator;
import ua.pinger.service.validator.URLValidator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountResourceService
{
    private static final Logger LOG = LoggerFactory.getLogger(AccountResourceService.class);
    @Autowired
    private AccountResourceRepository resourceRepository;
    @Autowired
    private MonitoringService monitoringService;
    @Autowired
    private ResponseAccountResourceMapper accountResourceMapper;
    @Autowired
    private HostValidator hostValidator;
    @Autowired
    private URLValidator urlValidator;

    public List<ResponseAccountResourceDto> getAll(int accountId)
    {
        List<AccountResource> accountResources = resourceRepository.findByAccountId(accountId);
        List<ResponseAccountResourceDto> responseResources = new ArrayList<>();
        for (AccountResource accountResource : accountResources)
        {
            ResponseAccountResourceDto resourceDto = accountResourceMapper.toDto(accountResource);
            responseResources.add(resourceDto);
        }

        return responseResources;
    }

    public ResponseAccountResourceDto getResource(int accountId, int id)
    {
        AccountResource accountResource = resourceRepository.findByAccountIdAndId(accountId, id);

        return accountResourceMapper.toDto(accountResource);
    }

    public AccountResource add(Account account, RequestCreateOrUpdateResourceDto resourceDto)
    {
        int countResources = resourceRepository.countByAccountId(account.getId());
        if (countResources >= account.getPlan().getResourceLimit())
        {
            throw new RestApiException("Tarif plan limit exceeded.");
        }

        String host = resourceDto.getHost().toLowerCase();
        MonitoringType type = resourceDto.getType();
        if (type == MonitoringType.URL)
        {
            validateUrl(host);
        }
        else if (type == MonitoringType.PING)
        {
            validateHost(host);
        }

        AccountResource resource = new AccountResource();
        resource.setName(resourceDto.getName());
        resource.setStatus(ResourceStatus.ACTIVE);
        resource.setHost(host);
        resource.setType(type);
        resource.setInterval(resourceDto.getMonitoringInterval());
        resource.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        resource.setAccountId(account.getId());
        LOG.info("IN add - account resource: successfully add");
        resource = resourceRepository.save(resource);
        monitoringService.enqueue(resource);
        return resource;
    }

    private void validateUrl(String host)
    {
        if (!urlValidator.isValid(host))
        {
            throw new RestApiException("Invalid url name");
        }
    }

    private void validateHost(String host)
    {
        if (!hostValidator.isValid(host))
        {
            throw new RestApiException("Invalid host name");
        }
    }

    public AccountResource update(int accountId, RequestCreateOrUpdateResourceDto resourceDto, int id)
    {

        AccountResource oldResource = resourceRepository.findByAccountIdAndId(accountId, id);
        if (oldResource != null)
        {
            oldResource.setName(resourceDto.getName());
            oldResource.setType(resourceDto.getType());
            oldResource.setHost(resourceDto.getHost());
            oldResource.setInterval(resourceDto.getMonitoringInterval());
            oldResource.setStatus(ResourceStatus.ACTIVE);
            oldResource.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            LOG.info("IN update - account resource: successfully update");
            return resourceRepository.save(oldResource);
        }
        throw new RestApiException(HttpStatus.BAD_REQUEST, "Not found account resource by id");
    }

    public void delete(int accountId, int id)
    {
        monitoringService.cancel(id);
        AccountResource resource = resourceRepository.findByAccountIdAndId(accountId, id);
        resourceRepository.delete(resource);
    }

    public AccountResource changeStatus(int idAccount, RequestChangeStatusDto changeStatusDto, int idResource)
    {
        ResourceStatus newStatus = changeStatusDto.getStatus();

        AccountResource oldAccountResource = resourceRepository.findByAccountIdAndId(idAccount, idResource);
        oldAccountResource.setStatus(newStatus);
        oldAccountResource = resourceRepository.save(oldAccountResource);

        if (newStatus == ResourceStatus.SUSPENDED || newStatus == ResourceStatus.ARCHIVED)
        {
            monitoringService.cancel(idResource);
        }
        else if (newStatus == ResourceStatus.ACTIVE)
        {
            monitoringService.enqueue(oldAccountResource);
        }

        return oldAccountResource;
    }

    public int getAllPageCount(int accountId)
    {
        int pageSize = 10;
        int allResourceCount = resourceRepository.findByAccountId(accountId).size();
        int pageCount = allResourceCount / pageSize + (allResourceCount % 10 > 0 ? 1 : 0);
        return Math.max(pageCount, 1);
    }
}
