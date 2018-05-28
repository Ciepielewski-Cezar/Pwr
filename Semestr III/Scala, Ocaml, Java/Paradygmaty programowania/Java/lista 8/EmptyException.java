import java.lang.Exception;


public class EmptyException extends Exception {
    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }
}
