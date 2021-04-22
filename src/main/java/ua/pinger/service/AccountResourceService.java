package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.RestApiException;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.enumeration.ResourceStatus;
import ua.pinger.dto.RequestChangeStatusDto;
import ua.pinger.dto.RequestCreateOrUpdateResourceDto;
import ua.pinger.repository.AccountResourceRepository;
import ua.pinger.service.monitoring.MonitoringService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountResourceService
{
    private static final Logger LOG = LoggerFactory.getLogger(AccountResourceService.class);
    @Autowired
    private AccountResourceRepository resourceRepository;
    @Autowired
    private MonitoringService monitoringService;


    public List<AccountResource> getAll(int accountId)
    {
        return resourceRepository.findByAccountId(accountId);
    }

    public AccountResource getResource(int accountId, int id)
    {
        return resourceRepository.findByAccountIdAndId(accountId, id);
    }

    public AccountResource add(Account account, RequestCreateOrUpdateResourceDto resourceDto)
    {
        int countResources = resourceRepository.countByAccountId(account.getId());
        if (countResources >= account.getPlan().getResourceLimit())
        {
            throw new RestApiException("Tarif plan limit exceeded.");
        }
        AccountResource resource = new AccountResource();
        resource.setName(resourceDto.getName());
        resource.setStatus(ResourceStatus.ACTIVE);
        resource.setHost(resourceDto.getHost());
        resource.setType(resourceDto.getType());
        resource.setInterval(resourceDto.getMonitoringInterval());
        resource.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        resource.setAccountId(account.getId());
        LOG.info("IN add - account resource: successfully add");
        resource = resourceRepository.save(resource);
        monitoringService.enqueue(resource);
        return resource;
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
        throw new RestApiException("Not found account resource by id");
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
}
