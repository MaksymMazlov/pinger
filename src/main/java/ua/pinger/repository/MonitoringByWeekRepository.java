package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pinger.domain.MonitoringByWeek;

@Repository
public interface MonitoringByWeekRepository extends JpaRepository<MonitoringByWeek, Integer>
{

}
