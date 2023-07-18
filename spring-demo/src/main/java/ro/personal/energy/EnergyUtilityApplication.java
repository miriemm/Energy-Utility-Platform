package ro.personal.energy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import ro.personal.energy.repositories.PersonRepository;
import ro.personal.energy.services.SensorService;

import java.util.TimeZone;

@SpringBootApplication
@Validated
@EnableScheduling
public class EnergyUtilityApplication extends SpringBootServletInitializer {

    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public SensorService sensorService;


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EnergyUtilityApplication.class);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(EnergyUtilityApplication.class, args);

    }

}





