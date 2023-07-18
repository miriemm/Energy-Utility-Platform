package ro.personal.energy.mapper;

import org.mapstruct.Mapper;
import ro.personal.energy.dtos.PersonDTO;
import ro.personal.energy.entities.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDto(Person person);

    List<PersonDTO> listToDto(List<Person> persons);
}
