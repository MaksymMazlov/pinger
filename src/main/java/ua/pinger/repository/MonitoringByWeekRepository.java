package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.MonitoringByWeek;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MonitoringByWeekRepository extends JpaRepository<MonitoringByWeek, Integer>
{
    List<MonitoringByWeek> findAllByAccountResourceIdAndDateBetween(int accountResourceId,LocalDate dateStart,LocalDate dateEnd);

    boolean existsByDateAndAccountResourceId(LocalDate date,int accountResourceId);
}
