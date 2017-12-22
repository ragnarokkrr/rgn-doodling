package ragna.exceptions;

public class BadRequestException extends  ApiException {
    public BadRequestException(int code, String msg) {
        super(code, msg);
    }
}
