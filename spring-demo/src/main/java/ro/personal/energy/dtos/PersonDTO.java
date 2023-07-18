package ro.personal.energy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Integer id;

    private String name;

    private LocalDate birthDate;

    private String address;

    private String role;

    private String username;

    private String password;

    private List<DeviceDTO> devices;
}
