package ro.personal.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
public class PersonDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Integer id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public PersonDetailsImpl(Integer id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static PersonDetailsImpl build(Person person) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));

        return new PersonDetailsImpl(
                person.getId(),
                person.getUsername(),
                person.getPassword(),
                authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        PersonDetailsImpl user = (PersonDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }

}
