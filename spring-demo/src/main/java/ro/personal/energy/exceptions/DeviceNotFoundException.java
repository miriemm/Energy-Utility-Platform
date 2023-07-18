package ro.personal.energy.exceptions;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException(Integer id){
        super("Device with id: " + id + " does not exist!");
    }
}
