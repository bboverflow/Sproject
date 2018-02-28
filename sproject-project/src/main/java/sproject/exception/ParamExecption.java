package sproject.exception;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/28 10:14
 * @Modified By:
 */
public class ParamExecption extends RuntimeException{
    public ParamExecption() {
        super();
    }

    public ParamExecption(String message) {
        super(message);
    }

    public ParamExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamExecption(Throwable cause) {
        super(cause);
    }

    protected ParamExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
