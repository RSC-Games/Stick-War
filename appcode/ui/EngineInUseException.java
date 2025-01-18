package appcode.ui;

public class EngineInUseException extends RuntimeException{
    public EngineInUseException(String message) {
        super(message);
    }
}
