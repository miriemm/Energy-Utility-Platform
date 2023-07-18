package ro.personal.energy.exceptions;

public class DeviceIdNullException extends RuntimeException{
    public DeviceIdNullException(){
        super("The device id must not be null!");
    }
}
