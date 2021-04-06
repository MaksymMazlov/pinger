package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pinger.domain.MonitoringByDay;

import java.util.List;

public interface MonitoringByDayRepository extends JpaRepository<MonitoringByDay, Integer>
{

}
