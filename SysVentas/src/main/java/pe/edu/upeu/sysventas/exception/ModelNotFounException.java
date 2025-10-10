package pe.edu.upeu.sysventas.exception;

import lombok.Getter;

@Getter
public class ModelNotFounException extends RuntimeException {
    private final int errorCode;
    public ModelNotFounException(String message){
        super(message);
        this.errorCode = 0;
    }

    public ModelNotFounException(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
