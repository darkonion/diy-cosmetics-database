package pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleExceptionNotFound(Exception e) {
        String error = "Item not found";
        return buildResponse(new ExceptionModel(HttpStatus.NOT_FOUND, error, e));
    }

    private ResponseEntity<Object> buildResponse(ExceptionModel exceptionModel) {
        return new ResponseEntity<>(exceptionModel, exceptionModel.getStatus());
    }
}
