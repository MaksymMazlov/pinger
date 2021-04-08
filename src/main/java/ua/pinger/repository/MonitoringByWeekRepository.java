package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pinger.domain.MonitoringByWeek;

public interface MonitoringByWeekRepository extends JpaRepository<MonitoringByWeek, Integer>
{

}
