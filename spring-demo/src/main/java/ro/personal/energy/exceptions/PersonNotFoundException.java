package ro.personal.energy.exceptions;

public class PersonNotFoundException extends RuntimeException{
    public  PersonNotFoundException(Integer id){
        super("Person with id: " + id + " does not exist!");
    }
}
