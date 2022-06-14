package exceptions;

public class InvalidPlayerNameException extends Exception{
    public InvalidPlayerNameException(String msg){
        super(msg);
    }
}
