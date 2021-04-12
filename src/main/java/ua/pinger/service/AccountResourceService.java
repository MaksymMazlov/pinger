package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.RestApiException;
import ua.pinger.domain.AccountResource;
import ua.pinger.dto.RequestCreateOrUpdateResourceDto;
import ua.pinger.repository.AccountResourceRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountResourceService
{
    private static final Logger LOG = LoggerFactory.getLogger(AccountResourceService.class);
    @Autowired
    private AccountResourceRepository resourceRepository;

    public List<AccountResource> getAll(int accountId)
    {
        return resourceRepository.findByAccountId(accountId);
    }

    public AccountResource getResource(int accountId, int id)
    {
        return resourceRepository.findByAccountIdAndId(accountId, id);
    }

    public AccountResource add(int accountId, RequestCreateOrUpdateResourceDto resourceDto)
    {
        AccountResource resource = new AccountResource();

        resource.setName(resourceDto.getName());
        resource.setStatus("Started");
        resource.setHost(resourceDto.getHost());
        resource.setType(resourceDto.getType());
        resource.setInterval(resourceDto.getMonitoringInterval());
        resource.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        resource.setAccountId(accountId);
        LOG.info("IN add - account resource: successfully add");
        return resourceRepository.save(resource);
    }

    public AccountResource update(int accountId, RequestCreateOrUpdateResourceDto resourceDto, int id)
    {

        AccountResource oldResource = resourceRepository.findByIdAndAccountId(id, accountId);
        if (oldResource != null)
        {
            oldResource.setName(resourceDto.getName());
            oldResource.setType(resourceDto.getType());
            oldResource.setHost(resourceDto.getHost());
            oldResource.setInterval(resourceDto.getMonitoringInterval());
            oldResource.setStatus("UPDATE");
            oldResource.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            LOG.info("IN update - account resource: successfully update");
            return resourceRepository.save(oldResource);
        }
        throw new RestApiException("Not found account resource by id");
    }

    public void delete(int accountId, int id)
    {
        AccountResource resource = resourceRepository.findByAccountIdAndId(accountId, id);
        resourceRepository.delete(resource);
    }
}
