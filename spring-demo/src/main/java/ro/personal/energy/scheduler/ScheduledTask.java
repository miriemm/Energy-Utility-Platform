package ro.personal.energy.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.personal.energy.entities.Device;
import ro.personal.energy.entities.Person;
import ro.personal.energy.entities.Sensor;
import ro.personal.energy.repositories.PersonRepository;
import ro.personal.energy.services.SensorService;

import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SensorService sensorService;

    @Scheduled(fixedRate = 30000)
        public void method(){
            List<Person> personList = personRepository.findAll();

            for(int i = 0; i < personList.size(); i++){
                Person person = personList.get(i);
                List<Device> deviceList = person.getDevices();
                for(int j = 0; j < deviceList.size(); j++){
                    Device device = deviceList.get(j);
                    Double baseline = device.getBaseline();
                    Sensor sensor = device.getSensor();
                    sensorService.updateMaxValue(baseline,sensor.getId());
                }
            }
        }

}
