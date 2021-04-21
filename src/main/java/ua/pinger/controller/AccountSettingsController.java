package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountSettings;
import ua.pinger.service.AccountSettingsService;

import java.util.List;

@RestController
@RequestMapping("/api/account/settings")
public class AccountSettingsController
{
    @Autowired
    private AccountSettingsService settingsService;

    @GetMapping
    public List<AccountSettings> allSettings(Authentication authentication)
    {
        Account account = (Account) authentication.getPrincipal();
        return settingsService.getAll(account.getId());
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountSettings saveSettingById(@PathVariable int id, @RequestBody AccountSettings accountSetting, Authentication authentication)
    {
        Account account = (Account) authentication.getPrincipal();
        int accountId = account.getId();
        return settingsService.updateSetting(accountId, accountSetting, id);
    }
}
