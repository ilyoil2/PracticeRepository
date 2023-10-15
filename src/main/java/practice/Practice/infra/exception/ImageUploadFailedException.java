package practice.Practice.infra.exception;

import practice.Practice.global.error.exception.BusinessException;
import practice.Practice.global.error.exception.ErrorCode;

public class ImageUploadFailedException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new ImageUploadFailedException();

    private ImageUploadFailedException() { super(ErrorCode.USER_NOT_FOUND);}
}
