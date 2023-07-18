package ro.personal.energy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

    private Integer id;

    private String description;

    private String location;

    private Double maxEnergyConsumption;

    private Double baseline;

    private SensorDTO sensor;

}
