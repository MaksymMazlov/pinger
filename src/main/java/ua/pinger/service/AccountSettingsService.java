package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.RestApiException;
import ua.pinger.domain.Account;
import ua.pinger.domain.AccountSettings;
import ua.pinger.repository.AccountSettingsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountSettingsService
{
    @Autowired
    private AccountSettingsRepository accountSettingsRepository;

    public List<AccountSettings> getAll(int accountId)
    {
        return accountSettingsRepository.findByAccountId(accountId);
    }

    public AccountSettings updateSetting(int accountId, AccountSettings setting, int id)
    {
        AccountSettings oldAccountSetting = accountSettingsRepository.findByIdAndAccountId(id,accountId);
        if (oldAccountSetting!=null)
        {
            oldAccountSetting.setValue(setting.getValue());
            return accountSettingsRepository.save(oldAccountSetting);
        }
        throw new RestApiException("Not found account settings by id");
    }
}
