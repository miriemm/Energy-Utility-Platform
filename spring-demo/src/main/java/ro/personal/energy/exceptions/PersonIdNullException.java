package ro.personal.energy.exceptions;

public class PersonIdNullException extends RuntimeException{
    public PersonIdNullException(){
        super("The person id must not be null!");
    }
}
