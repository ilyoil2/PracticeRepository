package practice.Practice.domain.user.exception;


import practice.Practice.global.error.exception.BusinessException;
import practice.Practice.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}