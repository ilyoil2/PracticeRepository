package practice.Practice.global.exception;


import practice.Practice.global.error.exception.BusinessException;
import practice.Practice.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();
    //
    private InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);

    }
}
