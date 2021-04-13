package ua.pinger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pinger.domain.AccountResource;

@Repository
public interface AccountResourceRepository extends JpaRepository<AccountResource, Integer>
{
    List<AccountResource> findByAccountId(int accountId);

    AccountResource findByAccountIdAndId(int accountId, int id);

    AccountResource findByIdAndAccountId(int id, int accountId);
}

