package ro.personal.energy.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.personal.energy.Utils;
import ro.personal.energy.dtos.DeviceDTO;
import ro.personal.energy.entities.Device;
import ro.personal.energy.entities.Person;
import ro.personal.energy.entities.Sensor;
import ro.personal.energy.exceptions.DeviceIdNullException;
import ro.personal.energy.exceptions.DeviceNotFoundException;
import ro.personal.energy.mapper.DeviceMapper;
import ro.personal.energy.repositories.DeviceRepository;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private Utils utils;

    @Autowired
    private PersonService personService;

    public List<DeviceDTO> allDevices(){
        List<Device> devices = deviceRepository.findAll();
        return deviceMapper.listToDto(devices);
    }

    public Device findById(Integer id){
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
    }

    public List<DeviceDTO> allPersonDevices(){
        // get the logged in person
        Integer personId = utils.getUserIdFromSecurityContext();
        Person person = personService.findById(personId);
        return deviceMapper.listToDto(person.getDevices());
    }

    public DeviceDTO create(DeviceDTO deviceDTO){
        Device device = new Device();
        BeanUtils.copyProperties(deviceDTO,device);

        // the sensor has to be searched and set
        Sensor sensor = sensorService.findById(deviceDTO.getSensor().getId());
        device.setSensor(sensor);
        deviceRepository.save(device);
        return deviceMapper.toDto(device);
    }

    public DeviceDTO edit(DeviceDTO deviceDTO){
        if(deviceDTO.getId() == null){
            throw new DeviceIdNullException();
        }
        Device existingDevice = findById(deviceDTO.getId());

        BeanUtils.copyProperties(deviceDTO, existingDevice, "id");


        Sensor sensor = sensorService.findById(deviceDTO.getSensor().getId());
        existingDevice.setSensor(sensor);

        deviceRepository.save(existingDevice);
        return deviceMapper.toDto(existingDevice);
    }

    public void deleteById(Integer id){
        Device device = findById(id);
        deviceRepository.delete(device);
    }

}
