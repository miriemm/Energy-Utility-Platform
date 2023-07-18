package ro.personal.energy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.personal.energy.entities.Person;
import ro.personal.energy.repositories.PersonRepository;
import ro.personal.energy.security.dto.SignupRequest;


@Component
public class AuthService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }


    public void register(SignupRequest signUpRequest) {
        Person person = Person.builder()
                .name(signUpRequest.getName())
                .birthDate(signUpRequest.getBirthDate())
                .address(signUpRequest.getAddress())
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role("ADMINISTRATOR")
                .build();

        personRepository.save(person);
    }
}
