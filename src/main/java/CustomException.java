import java.io.IOException;

public class CustomException extends IOException {

    public CustomException(){super();}
    public CustomException(String mensaje){super(mensaje);}
}
