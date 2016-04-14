package skyhao.exception;

/**
 * Created by 昊 on 2016/4/7.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(){}

    public DataAccessException(Throwable th){
        super(th);
    }

    public DataAccessException(Throwable th, String message){
        super(message, th);
    }
}
