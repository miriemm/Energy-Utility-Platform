package ro.personal.energy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.personal.energy.entities.MonitoredValue;
import ro.personal.energy.entities.Sensor;

import java.util.List;

public interface MonitoredValueRepository extends JpaRepository<MonitoredValue, Integer> {
}
