package ro.personal.energy.exceptions;

public class PersonAlreadyExistsException extends RuntimeException{
    public PersonAlreadyExistsException(String username){
        super("Person with username: " + username + " already registered!");
    }
}
