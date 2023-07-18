package ro.personal.energy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.personal.energy.entities.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Optional<Sensor> findById(Integer id);
}
