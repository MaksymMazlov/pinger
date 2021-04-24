package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{
    Account findByEmail(String email);

    Account findByToken(String token);
}
