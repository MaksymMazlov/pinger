package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pinger.domain.AccountResource;

public interface AccountResourceRepository extends JpaRepository<AccountResource, Long>
{

}
