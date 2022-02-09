package elp.max.e.core.exception;

public class WorkingDtoNotFoundException extends RuntimeException {

    public WorkingDtoNotFoundException(String message) {
        super(message + " не работают!");
    }
}
