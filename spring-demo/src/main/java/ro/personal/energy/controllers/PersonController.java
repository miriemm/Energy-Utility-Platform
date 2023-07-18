package ro.personal.energy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.personal.energy.UrlMapping;
import ro.personal.energy.dtos.PersonDTO;
import ro.personal.energy.services.PersonService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = UrlMapping.PERSON)
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDTO> allPersons(){
        return personService.allPersons();
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.create(personDTO));
    }

    @PatchMapping
    public ResponseEntity<PersonDTO> edit(@Valid @RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.edit(personDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody PersonDTO personDTO){
        personService.deleteById(personDTO.getId());
        return ResponseEntity.ok("Person with username: " + personDTO.getUsername() + " was deleted successfully!");
    }


}
