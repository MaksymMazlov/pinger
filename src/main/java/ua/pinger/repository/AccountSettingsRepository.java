package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.AccountSettings;

import java.util.List;

@Repository
public interface AccountSettingsRepository extends JpaRepository<AccountSettings, Integer>
{
    List<AccountSettings> findByAccountId(int accountId);

    AccountSettings findByIdAndAccountId(int id, int accountId);

    @Query("select a.value from AccountSettings a " +
            "where a.key=ua.pinger.domain.enumeration.AccountSetting.PHONE_NUMBER " +
            "and a.accountId=:accountId")
    String findPhoneByAccountId(@Param("accountId") int accountId);
}
