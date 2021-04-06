package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pinger.domain.AccountSettings;

import java.util.List;

public interface AccountSettingsRepository extends JpaRepository<AccountSettings, Integer>
{
    List<AccountSettings> findByAccountId(int accountId);

    AccountSettings findByIdAndAccountId(int id, int accountId);
}
