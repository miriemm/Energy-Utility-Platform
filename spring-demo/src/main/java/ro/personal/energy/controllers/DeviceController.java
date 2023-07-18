package ro.personal.energy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.personal.energy.UrlMapping;
import ro.personal.energy.dtos.DeviceDTO;
import ro.personal.energy.services.DeviceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = UrlMapping.DEVICE)
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<DeviceDTO> allDevices(){
        return deviceService.allDevices();
    }

    @GetMapping("/allPersonDevices")
    public List<DeviceDTO> allPersonDevices(){
        return deviceService.allPersonDevices();
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> create(@Valid @RequestBody DeviceDTO deviceDTO){
        return ResponseEntity.ok(deviceService.create(deviceDTO));
    }

    @PatchMapping
    public ResponseEntity<DeviceDTO> edit(@Valid @RequestBody DeviceDTO deviceDTO){
        return ResponseEntity.ok(deviceService.edit(deviceDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody DeviceDTO deviceDTO){
        deviceService.deleteById(deviceDTO.getId());
        return ResponseEntity.ok("Device with id: " + deviceDTO.getId() + " was deleted successfully!");
    }
}
