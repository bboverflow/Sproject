package sproject.exception;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/27 14:31
 * @Modified By:
 */
public class PermissionExecption extends RuntimeException{
    public PermissionExecption() {
        super();
    }

    public PermissionExecption(String message) {
        super(message);
    }

    public PermissionExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionExecption(Throwable cause) {
        super(cause);
    }

    protected PermissionExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
