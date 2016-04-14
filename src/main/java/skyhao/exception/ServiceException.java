package skyhao.exception;

/**
 * Created by 昊 on 2016/4/7.
 */
public class ServiceException extends RuntimeException{


    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(Throwable th){
        super(th);
    }

    public ServiceException(Throwable th, String message){
        super(message, th);
    }
}
