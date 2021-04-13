package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pinger.domain.MonitoringByDay;

@Repository
public interface MonitoringByDayRepository extends JpaRepository<MonitoringByDay, Integer>
{

}
