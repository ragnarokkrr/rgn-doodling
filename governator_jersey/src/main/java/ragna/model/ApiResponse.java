package ragna.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApiResponse {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;

    int code;
    String type;
    String message;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;

        switch (code) {
            case ERROR:
                setType ("error");
                break;
            case WARNING:
                setType ("warning");
                break;
            case INFO:
                setType ("info");
                break;
            case OK:
                setType ("ok");
                break;
            case TOO_BUSY:
                setType ("too busy");
                break;
            default:
                setType ("unknown");
                break;
        }
    }

    public void setType(String type) {
        this.type = type;
    }
}
