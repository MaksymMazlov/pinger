package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountResource;
import ua.pinger.dto.RequestChangeStatusDto;
import ua.pinger.dto.RequestCreateOrUpdateResourceDto;
import ua.pinger.dto.ResponseAccountResourceDto;
import ua.pinger.dto.ResponseMonitorByDayUptime;
import ua.pinger.repository.AccountResourcePagesRepository;
import ua.pinger.service.AccountResourceService;
import ua.pinger.service.MonitoringByDayService;

import java.util.List;

@RestController
public class ResourceController
{
    @Autowired
    private AccountResourceService resourceService;
    @Autowired
    private MonitoringByDayService monitoringByDayService;
    @Autowired
    private AccountResourcePagesRepository resourcePagesRepository;

    @GetMapping("/api/resources")
    public List<ResponseAccountResourceDto> getResources(Authentication authentication)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.getAll(account.getId());
    }

    @GetMapping("/api/resources/page/{page}")
    public List<AccountResource> getResourcesForPage(Authentication authentication,@PathVariable int page)
    {
        Account account = (Account) authentication.getPrincipal();
        PageRequest pageRequest = PageRequest.of(page,10);
        return resourcePagesRepository.findAllByAccountIdOrderByIdDesc(account.getId(),pageRequest);
    }

    @GetMapping("/api/resource/{id}")
    public ResponseAccountResourceDto getResource(Authentication authentication, @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.getResource(account.getId(), id);
    }

    @PostMapping(value = "/api/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource addResource(Authentication authentication,
                                       @RequestBody @Validated RequestCreateOrUpdateResourceDto resourceDto)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.add(account, resourceDto);
    }

    @PutMapping(value = "/api/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource updateResource(Authentication authentication,
                                          @RequestBody @Validated RequestCreateOrUpdateResourceDto resourceDto,
                                          @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.update(account.getId(), resourceDto, id);
    }

    @DeleteMapping("/api/resource/{id}")
    public void deleteResource(Authentication authentication, @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        resourceService.delete(account.getId(), id);
    }

    @PatchMapping(value = "/api/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource changeStatus(Authentication authentication,
                                        @RequestBody @Validated RequestChangeStatusDto changeStatusDto,
                                        @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.changeStatus(account.getId(), changeStatusDto, id);
    }

    @GetMapping(value = "/api/resource/{id}/uptime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMonitorByDayUptime getUptime(Authentication authentication, @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();

        return monitoringByDayService.getUptimeForPercent(account.getId(), id);
    }
}
