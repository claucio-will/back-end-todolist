package br.com.claucio.dev.todolist.infrastructure.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class RestResponseError {

    private String error;

    private RestResponseError(){

    }

    public  static  RestResponseError fromValidationError(Errors errors){
        RestResponseError restError = new RestResponseError();
        StringBuilder sb = new StringBuilder();

        for (ObjectError error : errors.getAllErrors()){
            sb.append(error.getDefaultMessage()).append(". ");
        }
        restError.error = sb.toString();
        return restError;
    }

    public String getError() {
        return error;
    }

    public static RestResponseError fromValidationMessage(String message){
        RestResponseError resp = new RestResponseError();
        resp.error = message;
        return resp;
    }
}
