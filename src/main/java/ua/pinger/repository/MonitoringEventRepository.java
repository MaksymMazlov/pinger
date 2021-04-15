package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.MonitoringEvents;

import java.util.List;

@Repository
public interface MonitoringEventRepository extends JpaRepository<MonitoringEvents, Integer>
{

    @Query("select me from MonitoringEvents me " +
            "where me.accountResource.id = :accountResourceId " +
            "and me.accountResource.account.id = :accountId")
    List<MonitoringEvents> findAllByAccountResourceId(@Param("accountResourceId") int accountResourceId,
                                                      @Param("accountId") int accountId);
}