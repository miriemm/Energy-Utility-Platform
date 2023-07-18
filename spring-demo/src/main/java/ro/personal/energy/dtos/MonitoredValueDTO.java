package ro.personal.energy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitoredValueDTO {
    private Integer id;

    private LocalDateTime dateTime;

    private Double value;
}
