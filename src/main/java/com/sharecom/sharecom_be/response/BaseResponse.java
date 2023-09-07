package com.sharecom.sharecom_be.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "result"})
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private final Date timestamp;
    private final HttpStatus httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response; //DTO

    // 요청 성공
    public BaseResponse(T result) {
        this.message = "SUCCESS!";
        this.code = 200;
        this.timestamp = new Date();
        this.httpStatus = HttpStatus.OK;
        this.response = result;
    }

    // 요청 실패
    public BaseResponse(BaseResponseStatus status) {
        this.message = status.getMessage();
        this.code = status.getCode();
        this.timestamp = new Date();
        this.httpStatus = status.getHttpStatus();
    }

}
