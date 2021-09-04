package br.com.claucio.dev.todolist.exception;

public class DuplicateTaskException extends Exception{

    public DuplicateTaskException(String message) {
        super(message);
    }
}
