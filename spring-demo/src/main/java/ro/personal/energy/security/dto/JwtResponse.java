package ro.personal.energy.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtResponse {

    private String token;
    private Integer id;
    private String username;
    private String role;

}