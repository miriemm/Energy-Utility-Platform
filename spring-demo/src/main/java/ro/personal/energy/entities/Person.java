package ro.personal.energy.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(pattern= "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String role; // can be admin or client

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 120)
    private String password;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_devices",
    joinColumns = {@JoinColumn(name = "person_id")},
    inverseJoinColumns = {@JoinColumn(name = "device_id")})
    private List<Device> devices;
}


