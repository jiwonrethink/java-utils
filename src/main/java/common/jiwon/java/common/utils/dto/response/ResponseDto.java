package common.jiwon.java.common.utils.dto.response;

import common.jiwon.java.common.utils.constant.ResponseStatus;
import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private final ResponseStatus status;
    private final int code;
    private T data;

    public ResponseDto(ResponseStatus status, int code, T data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public ResponseDto(ResponseStatus status, int code) {
        this.status = status;
        this.code = code;
    }
}