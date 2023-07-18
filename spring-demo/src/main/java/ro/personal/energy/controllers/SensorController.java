package ro.personal.energy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.personal.energy.UrlMapping;
import ro.personal.energy.dtos.SensorDTO;
import ro.personal.energy.services.SensorService;

import java.util.List;

@RestController
@RequestMapping(value = UrlMapping.SENSOR)
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<SensorDTO> allSensors(){
        return  sensorService.allSensors();
    }

    @GetMapping("/allPersonSensors")
    public List<SensorDTO> allPersonDevices(){
        return sensorService.allPersonSensors();
    }

    @PostMapping
    public ResponseEntity<SensorDTO> create(@RequestBody SensorDTO sensorDTO){
        return ResponseEntity.ok(sensorService.create(sensorDTO));
    }

    @PatchMapping
    public ResponseEntity<SensorDTO> edit(@RequestBody SensorDTO sensorDTO){
        return ResponseEntity.ok(sensorService.edit(sensorDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody SensorDTO sensorDTO){
        sensorService.deleteById(sensorDTO.getId());
        return ResponseEntity.ok("Sensor with id: " + sensorDTO.getId() + " was deleted successfully!");
    }
}
