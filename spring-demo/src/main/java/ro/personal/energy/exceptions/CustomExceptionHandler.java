package ro.personal.energy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    protected ResponseEntity<Object> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    protected ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonIdNullException.class)
    protected ResponseEntity<Object> handlePersonIdNullException(PersonIdNullException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorNotFoundException.class)
    protected ResponseEntity<Object> handleSensorNotFoundException(SensorNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorIdNullException.class)
    protected ResponseEntity<Object> handleSensorIdNullException(SensorIdNullException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    protected ResponseEntity<Object> handleDeviceNotFoundException(DeviceNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceIdNullException.class)
    protected ResponseEntity<Object> handleDeviceIdNullException(DeviceIdNullException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
