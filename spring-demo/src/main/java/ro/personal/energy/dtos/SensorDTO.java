package ro.personal.energy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {

    private Integer id;

    private String description;

    private Double maxValue;

    private List<MonitoredValueDTO> monitoredValueDTOList;

}
