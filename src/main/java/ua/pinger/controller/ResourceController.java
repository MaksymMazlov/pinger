package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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
import ua.pinger.service.AuthorizationService;
import ua.pinger.service.MonitoringByDayService;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private AccountResourceService resourceService;
    @Autowired
    private MonitoringByDayService monitoringByDayService;
    @Autowired
    private AccountResourcePagesRepository resourcePagesRepository;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/api/resources")
    public List<ResponseAccountResourceDto> getResources() {
        Account account = authorizationService.currentAccount();
        return resourceService.getAll(account.getId());
    }

    @GetMapping("/api/resources/page/{page}")
    public List<AccountResource> getResourcesForPage(@PathVariable int page) {
        Account account = authorizationService.currentAccount();
        PageRequest pageRequest = PageRequest.of(page, 10);
        return resourcePagesRepository.findAllByAccountIdOrderByIdDesc(account.getId(), pageRequest);
    }

    @GetMapping("/api/resource/{id}")
    public ResponseAccountResourceDto getResource(@PathVariable int id) {
        Account account = authorizationService.currentAccount();
        return resourceService.getResource(account.getId(), id);
    }

    @PostMapping(value = "/api/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource addResource(@RequestBody @Validated RequestCreateOrUpdateResourceDto resourceDto) {
        Account account = authorizationService.currentAccount();
        return resourceService.add(account, resourceDto);
    }

    @PutMapping(value = "/api/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource updateResource(@RequestBody @Validated RequestCreateOrUpdateResourceDto resourceDto,
                                          @PathVariable int id) {
        Account account = authorizationService.currentAccount();
        return resourceService.update(account.getId(), resourceDto, id);
    }

    @DeleteMapping("/api/resource/{id}")
    public void deleteResource(@PathVariable int id) {
        Account account = authorizationService.currentAccount();
        resourceService.delete(account.getId(), id);
    }

    @PatchMapping(value = "/api/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource changeStatus(@RequestBody @Validated RequestChangeStatusDto changeStatusDto,
                                        @PathVariable int id) {
        Account account = authorizationService.currentAccount();
        return resourceService.changeStatus(account.getId(), changeStatusDto, id);
    }

    @GetMapping(value = "/api/resource/{id}/uptime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMonitorByDayUptime getUptime(@PathVariable int id) {
        Account account = authorizationService.currentAccount();
        return monitoringByDayService.getUptimeForPercent(account.getId(), id);
    }
}
