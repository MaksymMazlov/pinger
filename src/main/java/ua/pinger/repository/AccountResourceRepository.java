package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.AccountResource;

import java.util.List;

@Repository
public interface AccountResourceRepository extends JpaRepository<AccountResource, Integer>
{
    List<AccountResource> findByAccountId(int accountId);

    AccountResource findByAccountIdAndId(int accountId, int id);

    int countByAccountId(int accountId);
}

