package ua.pinger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pinger.domain.MonitoringEvents;

@Repository
public interface MonitoringEventRepository extends JpaRepository<MonitoringEvents, Integer>
{

}