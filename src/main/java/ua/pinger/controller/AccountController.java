package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.dto.RequestAccountRegistrationDto;
import ua.pinger.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Account register(@RequestBody @Validated RequestAccountRegistrationDto dto)
    {
        return accountService.register(dto);
    }
}
