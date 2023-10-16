package practice.Practice.domain.user.exception;


import practice.Practice.global.error.exception.BusinessException;
import practice.Practice.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException(){
        super (ErrorCode.USER_NOT_FOUND);
    }
}