package ro.personal.energy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.personal.energy.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(Integer id);
    Optional<Person> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<Person> findAllByRole(String role);
}
