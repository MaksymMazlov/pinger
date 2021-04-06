package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountResource;
import ua.pinger.dto.RequestCreateOrUpdateResourceDto;
import ua.pinger.service.AccountResourceService;

import java.util.List;

@RestController
public class ResourceController
{
    @Autowired
    AccountResourceService resourceService;

    @GetMapping("/resources")
    public List<AccountResource> getResources(Authentication authentication)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.getAll(account.getId());
    }

    @GetMapping("/resource/{id}")
    public AccountResource getResource(Authentication authentication, @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.getResource(account.getId(), id);
    }

    @PostMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource addResource(Authentication authentication,
                                       @RequestBody  @Validated RequestCreateOrUpdateResourceDto resourceDto)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.add(account.getId(), resourceDto);
    }


    @PutMapping(value = "/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResource updateResource(Authentication authentication,
                                          @RequestBody @Validated RequestCreateOrUpdateResourceDto resourceDto,
                                          @PathVariable int id)
    {
        Account account = (Account) authentication.getPrincipal();
        return resourceService.update(account.getId(), resourceDto, id);
    }
}
