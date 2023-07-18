package ro.personal.energy.exceptions;

public class SensorNotFoundException extends RuntimeException{
    public  SensorNotFoundException(Integer id){
        super("Sensor with id: " + id + " does not exist!");
    }
}
