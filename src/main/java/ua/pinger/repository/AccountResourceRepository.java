package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pinger.domain.AccountResource;

import java.util.List;

public interface AccountResourceRepository extends JpaRepository<AccountResource, Integer>
{
    List<AccountResource> findByAccountId(int accountId);

    AccountResource findByAccountIdAndId(int accountId, int id);

    AccountResource findByIdAndAccountId(int id, int accountId);
}

