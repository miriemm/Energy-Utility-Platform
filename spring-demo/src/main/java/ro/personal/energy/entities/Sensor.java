package ro.personal.energy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column()
    private Double maxValue;

    @OneToOne(mappedBy = "sensor")
    private Device device;


    @OneToMany( mappedBy = "sensor")
    private List<MonitoredValue> monitoredValueList;
}
