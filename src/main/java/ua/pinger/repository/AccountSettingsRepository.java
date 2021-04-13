package ua.pinger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pinger.domain.AccountSettings;

@Repository
public interface AccountSettingsRepository extends JpaRepository<AccountSettings, Integer>
{
    List<AccountSettings> findByAccountId(int accountId);

    AccountSettings findByIdAndAccountId(int id, int accountId);
}
