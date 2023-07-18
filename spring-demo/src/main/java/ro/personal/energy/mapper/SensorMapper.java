package ro.personal.energy.mapper;

import org.mapstruct.Mapper;
import ro.personal.energy.dtos.SensorDTO;
import ro.personal.energy.entities.Sensor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorDTO toDto(Sensor sensor);

    List<SensorDTO> listToDto(List<Sensor> sensors);
}
