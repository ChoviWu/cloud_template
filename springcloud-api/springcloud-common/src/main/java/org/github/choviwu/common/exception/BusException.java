package org.github..common.exception;

/**
 * @author
 * @date 2018/7/6
 * Description : exceptinon bussiness class
 */
public final class BusException extends RuntimeException{

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusException(Integer code) {
        super("");
        this.code = code;
    }
    public BusException(String message) {
        super(message);
        this.message = message;
    }
    public BusException(Integer code,String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BusException(ExceptionMessage message) {
        super(message.getMsg());
        this.message = message.getMsg();
        this.code = message.getCode();
    }
}
