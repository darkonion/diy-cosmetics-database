package pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleExceptionNotFound(Exception e) {
        String error = "Item not found";
        return buildResponse(new ExceptionModel(HttpStatus.NOT_FOUND, error, e));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleExceptionDataIntegrityViolation(Exception e) {
        String error = "Data Integrity Violation - You are trying to remove data linked with other data.";
        return buildResponse(new ExceptionModel(HttpStatus.CONFLICT, error, e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleExceptionArgumentTypeMismatch(Exception e) {
        String error = "Incorrect number format";
        return buildResponse(new ExceptionModel(HttpStatus.BAD_REQUEST, error, e));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return buildResponse(new ExceptionModel(HttpStatus.BAD_REQUEST, request.getDescription(true), ex));
    }


    //support-----------

    private ResponseEntity<Object> buildResponse(ExceptionModel exceptionModel) {
        return new ResponseEntity<>(exceptionModel, exceptionModel.getStatus());
    }
}
