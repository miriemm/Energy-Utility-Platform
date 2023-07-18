package ro.personal.energy.mapper;

import org.mapstruct.Mapper;
import ro.personal.energy.dtos.DeviceDTO;
import ro.personal.energy.entities.Device;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDTO toDto(Device device);

    Device fromDto(DeviceDTO deviceDTO);

    List<DeviceDTO> listToDto(List<Device> devices);

    List<Device> listFromDto(List<DeviceDTO> devices);
}
