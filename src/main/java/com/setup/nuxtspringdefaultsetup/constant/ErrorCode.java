package com.setup.nuxtspringdefaultsetup.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * 공통으로 사용할 에러들을 정의
 * 무언가의 중복체크 같은 에러들은 각각의 Entity에 정의해 결합도를 높인다.
 *
 * code를 따로 정의해주는 이유: HttpStatus로 주는 코드는 일정하기에
 * 우리가 어떤 에러를 뱉었는지 더 정확히 해주기 위해서다. 또한 알지 못하는 에러를 뱉기보다는
 * 컨트롤 할 수 있는 에러내에서 던져주기 위함이다.
 *
 * ex): Bad Request는 400 Bad Request 라고만 던져준다. 다른 문제로 BAD_REQUEST가 나더라도
 * 계속 400 코드만 주기보다는 좀 더 상세하게 전달해준다면 에러 파악이 쉬워질것이다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, HttpStatus.OK, "Ok"),

    BAD_REQUEST(10000, HttpStatus.BAD_REQUEST, "Bad Request"),

    VALIDATION_ERROR(10001, HttpStatus.BAD_REQUEST, "Validation error"),

    NOT_FOUND(10002,HttpStatus.BAD_REQUEST, "Request resource is not found"),

    INTERNAL_ERROR(20000, HttpStatus.INTERNAL_SERVER_ERROR, "Internal error"),

    DATA_ACCESS_ERROR(20002, HttpStatus.INTERNAL_SERVER_ERROR, "Data access error");


    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage(Throwable e){
        String message = this.getMessage();
        if(e.getMessage() != null){
            message += " - " + e.getMessage();
        }
        return this.getMessage(message);
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    public Integer getCode() {
        return this.code;
    }


}
