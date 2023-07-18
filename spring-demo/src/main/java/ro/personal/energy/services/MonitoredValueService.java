package ro.personal.energy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.personal.energy.dtos.MonitoredValueDTO;
import ro.personal.energy.entities.MonitoredValue;
import ro.personal.energy.entities.Sensor;
import ro.personal.energy.mapper.MonitoredValueMapper;
import ro.personal.energy.repositories.MonitoredValueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitoredValueService {

    @Autowired
    private MonitoredValueRepository monitoredValueRepository;

    @Autowired
    private MonitoredValueMapper monitoredValueMapper;


    public List<MonitoredValueDTO> findAllMonitoredValuesBySensor(Integer sensorId){
        List<MonitoredValue> monitoredValueList = monitoredValueRepository.findAll();
        List<MonitoredValue> found = new ArrayList<>();

        for (MonitoredValue monitoredValue: monitoredValueList){
            Sensor sensor1 = monitoredValue.getSensor();

            if(sensor1.getId() == sensorId){
                found.add(monitoredValue);
            }
        }
        return monitoredValueMapper.listToDto(found);
    }


}
