package ro.personal.energy.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.personal.energy.entities.Person;
import ro.personal.energy.entities.PersonDetailsImpl;
import ro.personal.energy.repositories.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return PersonDetailsImpl.build(person);
    }
}
