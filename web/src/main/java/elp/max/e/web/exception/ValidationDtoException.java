package elp.max.e.web.exception;

public class ValidationDtoException extends RuntimeException{

    public ValidationDtoException(String message) {
        super("Валидация не пройдена: " + message);
    }
}
