package utils.common.util;

import utils.common.constant.ResponseStatus;
import utils.common.dto.response.ResponseDto;
import utils.common.error.ErrorCode;
import utils.common.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ResponseUtil {

    public static ResponseEntity SUCCESS () {
        return ResponseEntity.ok(new ResponseDto(ResponseStatus.SUCCESS, HttpServletResponse.SC_OK));
    }

    public static <T>ResponseEntity SUCCESS(T data) {
        return ResponseEntity.ok(new ResponseDto(ResponseStatus.SUCCESS, HttpServletResponse.SC_OK, data));
    }

    public static <T>ResponseEntity ERROR (int code, String message) {
        log.error(message);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(code))
                .body(new ResponseDto(
                        ResponseStatus.ERROR,
                        code,
                        new ErrorResponseDto(ErrorCode.SERVER_ERROR)
                ));
    }

    public static <T>ResponseEntity ERROR (int code, ErrorCode errorCode) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(code))
                .body(new ResponseDto(
                        ResponseStatus.ERROR,
                        code,
                        new ErrorResponseDto(errorCode)
                ));
    }
}
