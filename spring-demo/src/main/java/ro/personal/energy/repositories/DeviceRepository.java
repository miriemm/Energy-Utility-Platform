package ro.personal.energy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.personal.energy.entities.Device;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Integer> {
    Optional<Device> findById(Integer id);

}
