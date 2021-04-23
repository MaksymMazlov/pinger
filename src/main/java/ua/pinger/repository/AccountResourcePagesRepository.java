package ua.pinger.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.AccountResource;

import java.util.List;

@Repository
public interface AccountResourcePagesRepository extends PagingAndSortingRepository<AccountResource, Integer>
{
    List<AccountResource> findAllByAccountIdOrderByIdDesc(int accountId, Pageable p);
}