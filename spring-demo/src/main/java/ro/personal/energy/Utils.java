package ro.personal.energy;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ro.personal.energy.entities.PersonDetailsImpl;

@RequiredArgsConstructor
@Component
public class Utils {

    public Integer getUserIdFromSecurityContext()
    {
        return ((PersonDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}