package ro.personal.energy.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.personal.energy.Utils;
import ro.personal.energy.dtos.PersonDTO;
import ro.personal.energy.entities.Person;
import ro.personal.energy.exceptions.PersonAlreadyExistsException;
import ro.personal.energy.exceptions.PersonIdNullException;
import ro.personal.energy.exceptions.PersonNotFoundException;
import ro.personal.energy.mapper.DeviceMapper;
import ro.personal.energy.mapper.PersonMapper;
import ro.personal.energy.repositories.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private Utils utils;


    @Transactional
    public List<PersonDTO> allPersons(){
        // find all persons with role CLIENT
        return personMapper.listToDto(personRepository.findAllByRole("CLIENT"));
    }

    public Person findById(Integer id){
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    // CRUD operations on clients
    public PersonDTO create(PersonDTO personDTO){

        Person person = Person.builder()
                .name(personDTO.getName())
                .birthDate(personDTO.getBirthDate())
                .address(personDTO.getAddress())
                .username(personDTO.getUsername())
                .password(passwordEncoder.encode(personDTO.getPassword()))
                .role("CLIENT")
                .devices(deviceMapper.listFromDto(personDTO.getDevices()))
                .build();


        if(personRepository.existsByUsername(person.getUsername())){
            throw new PersonAlreadyExistsException(person.getUsername());
        }

        personRepository.save(person);
        return personMapper.toDto(person);
    }

    public PersonDTO edit(PersonDTO personDTO){
        if(personDTO.getId() == null){
            throw new PersonIdNullException();
        }
        Person existingPerson = findById(personDTO.getId());
        // The properties I want to be ignored when editing a client are id and role
        // These properties should not be modified, the id remains the same
        // and the role will always be "client"
        BeanUtils.copyProperties(personDTO, existingPerson, "id");
        existingPerson.setRole("CLIENT");
        existingPerson.setDevices(deviceMapper.listFromDto(personDTO.getDevices()));
        personRepository.save(existingPerson);
        return personMapper.toDto(existingPerson);

    }

    public void deleteById(Integer id){
        Person existingPerson = findById(id);
        personRepository.delete(existingPerson);

    }


}
