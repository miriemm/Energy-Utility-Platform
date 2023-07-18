package ro.personal.energy.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;
    @JsonFormat(pattern= "yyyy-MM-dd")
    private LocalDate birthDate;
    private String address;
    private String username;
    private String password;
    private String role;
}