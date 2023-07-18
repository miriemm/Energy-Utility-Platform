package ro.personal.energy.exceptions;

public class SensorIdNullException extends RuntimeException{
    public SensorIdNullException(){
        super("The sensor id must not be null!");
    }

}
