package ro.personal.energy.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.personal.energy.UrlMapping;
import ro.personal.energy.entities.PersonDetailsImpl;
import ro.personal.energy.security.dto.JwtResponse;
import ro.personal.energy.security.dto.LoginRequest;
import ro.personal.energy.security.dto.MessageResponse;
import ro.personal.energy.security.dto.SignupRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlMapping.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping(UrlMapping.SIGN_IN)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        PersonDetailsImpl personDetails = (PersonDetailsImpl) authentication.getPrincipal();

        List<String> roles = personDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                JwtResponse.builder()
                        .token(jwt)
                        .id(personDetails.getId())
                        .username(personDetails.getUsername())
                        .role(roles.get(0))
                        .build()
        );
    }

    @PostMapping(UrlMapping.SIGN_UP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }


        authService.register(signUpRequest);


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}