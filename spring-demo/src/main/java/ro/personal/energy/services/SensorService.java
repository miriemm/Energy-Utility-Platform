package ro.personal.energy.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.personal.energy.Utils;
import ro.personal.energy.dtos.SensorDTO;
import ro.personal.energy.entities.Device;
import ro.personal.energy.entities.MonitoredValue;
import ro.personal.energy.entities.Person;
import ro.personal.energy.entities.Sensor;
import ro.personal.energy.exceptions.SensorIdNullException;
import ro.personal.energy.exceptions.SensorNotFoundException;
import ro.personal.energy.mapper.MonitoredValueMapper;
import ro.personal.energy.mapper.SensorMapper;
import ro.personal.energy.repositories.MonitoredValueRepository;
import ro.personal.energy.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorMapper sensorMapper;

    @Autowired
    private Utils utils;

    @Autowired
    private PersonService personService;

    @Autowired
    private MonitoredValueRepository monitoredValueRepository;

    @Autowired
    private MonitoredValueService monitoredValueService;

    @Autowired
    private MonitoredValueMapper monitoredValueMapper;


    public List<SensorDTO> allSensors(){
        return sensorMapper.listToDto(sensorRepository.findAll());
    }

    public Sensor findById(Integer id){
        return sensorRepository.findById(id)
                .orElseThrow(() -> new SensorNotFoundException(id));
    }

    public List<SensorDTO> allPersonSensors(){
        // get the logged in person
        Integer personId = utils.getUserIdFromSecurityContext();
        Person person = personService.findById(personId);
        List<Device> devices = person.getDevices();
        List<Sensor> sensors = new ArrayList<>();

        // for each device, get the sensors and store them in a list
        for(Device device: devices){
            sensors.add(device.getSensor());
        }
        return sensorMapper.listToDto(sensors);
    }

    public SensorDTO create(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO,sensor);
        // the initial maximum value for a newly created sensor will be 0
        // this value will be updated when the sensor is starting to consume energy
        // meaning, it is associated to a device and the device is used by a person
        sensor.setMaxValue(0.0);
        sensorRepository.save(sensor);
        return sensorMapper.toDto(sensor);
    }

    public SensorDTO edit(SensorDTO sensorDTO){
        // for modifying an entity, we'll have to specify the id
        // if this id is null, an exception will be thrown
        if(sensorDTO.getId() == null){
            throw new SensorIdNullException();
        }
        Sensor existingSensor = findById(sensorDTO.getId());
        BeanUtils.copyProperties(sensorDTO,existingSensor,"id");
        sensorRepository.save(existingSensor);
        return sensorMapper.toDto(existingSensor);
    }

    public void deleteById(Integer id){
        // for deleting an entity, we'll have to specify the id
        // if this id is null, an exception will be thrown
        if(id == null){
            throw new SensorIdNullException();
        }
        Sensor sensor = findById(id);
        sensorRepository.delete(sensor);
    }


    public void updateMaxValue(Double baseline, Integer sensorId){
        Sensor sensor = findById(sensorId);

        MonitoredValue monitoredValue = new MonitoredValue();
        LocalDateTime dateTime = LocalDateTime.now();
        monitoredValue.setDateTime(dateTime);

        List<MonitoredValue> monitoredValueList = monitoredValueMapper.listFromDto(monitoredValueService.findAllMonitoredValuesBySensor(sensorId));

        // In case the list is empty, set the initial value to 0
        if(monitoredValueList.isEmpty()){
            monitoredValue.setValue(0.0);
            monitoredValue.setSensor(sensor);

            // I add the newly created monitoredValue to the list because
            // save method will not immediately save the data to the db
            monitoredValueList.add(monitoredValueRepository.save(monitoredValue));
        }

        // get the last monitored value
        MonitoredValue lastMonitoredValue = monitoredValueList.get(monitoredValueList.size() - 1);

        long seconds = ChronoUnit.SECONDS.between(lastMonitoredValue.getDateTime(), dateTime);

        // We know that the baseline energy consumption is per hour
        // In this case, we measure every 30 seconds => we divide the baseline * seconds to 3600 seconds(1 hour)
        monitoredValue.setValue(lastMonitoredValue.getValue() + baseline * seconds/ 3600);
        monitoredValue.setSensor(sensor);
        monitoredValueRepository.save(monitoredValue);


        sensor.setMaxValue(lastMonitoredValue.getValue());
        sensor.setMonitoredValueList(monitoredValueList);
        sensorRepository.save(sensor);

    }

}
