package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.MonitoringByDay;

import java.util.List;

@Repository
public interface MonitoringByDayRepository extends JpaRepository<MonitoringByDay, Integer>
{
    MonitoringByDay findTopByResourceIdOrderByIdDesc(int resourceId);

    @Query("select md from MonitoringByDay md " +
            "where md.accountResource.id = :accountResourceId " +
            "and md.accountResource.account.id = :accountId")
    List<MonitoringByDay> findAllByAccountIdAndResourceId(@Param("accountId") int accountId,
                                                          @Param("accountResourceId") int resourceId);
}
