package desafio.itau.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BadRequestException extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<String> basRequestException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request")
    }
}
