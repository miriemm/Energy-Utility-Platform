package ro.personal.energy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.personal.energy.UrlMapping;
import ro.personal.energy.dtos.MonitoredValueDTO;
import ro.personal.energy.services.MonitoredValueService;

import java.util.List;

@RestController
@RequestMapping(value = UrlMapping.MONITORED_VALUE)
public class MonitoredValueController {

    @Autowired
    private MonitoredValueService monitoredValueService;

    @GetMapping("/{sensorId}")
    public List<MonitoredValueDTO> findAllMonitoredValuesBySensor(@PathVariable Integer sensorId){
        return monitoredValueService.findAllMonitoredValuesBySensor(sensorId);
    }
}
