package ro.personal.energy.mapper;

import org.mapstruct.Mapper;
import ro.personal.energy.dtos.MonitoredValueDTO;
import ro.personal.energy.entities.MonitoredValue;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonitoredValueMapper {
    MonitoredValueDTO toDto(MonitoredValue monitoredValue);

    MonitoredValue fromDto(MonitoredValueDTO monitoredValueDTO);

    List<MonitoredValueDTO> listToDto(List<MonitoredValue> monitoredValueList);

    List<MonitoredValue> listFromDto(List<MonitoredValueDTO> monitoredValueDTOList);

}
