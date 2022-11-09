package LAB2.ExceptionMass;

import java.io.IOException;

public class FileNotFoundedException extends IOException {
    public FileNotFoundedException(String message){
        super(message);
    }
}
