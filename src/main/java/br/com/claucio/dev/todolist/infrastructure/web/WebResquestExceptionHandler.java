package br.com.claucio.dev.todolist.infrastructure.web;

import br.com.claucio.dev.todolist.exception.DuplicateTaskException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebResquestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(RepositoryConstraintViolationException e){
      return  RestResponseError.fromValidationError(e.getErrors());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleExceptionM(DuplicateTaskException e){
        return  RestResponseError.fromValidationMessage(e.getMessage());
    }
}
