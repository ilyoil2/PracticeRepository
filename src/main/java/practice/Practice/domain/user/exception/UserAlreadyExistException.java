package practice.Practice.domain.user.exception;


import practice.Practice.global.error.exception.BusinessException;
import practice.Practice.global.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();

    private UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}