package rockpaperscissors;

public class WrongOptionException extends RuntimeException {

    public WrongOptionException(String message) {
        super(message);
    }
}
