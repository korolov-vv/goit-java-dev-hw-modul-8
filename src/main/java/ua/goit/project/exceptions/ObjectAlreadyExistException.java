package ua.goit.project.exceptions;

public class ObjectAlreadyExistException extends RuntimeException {
    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}