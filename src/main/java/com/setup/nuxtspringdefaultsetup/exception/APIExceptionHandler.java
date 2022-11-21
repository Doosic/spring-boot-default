package com.setup.nuxtspringdefaultsetup.exception;

import com.setup.nuxtspringdefaultsetup.common.APIErrorResponse;
import com.setup.nuxtspringdefaultsetup.constant.ErrorCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static com.setup.nuxtspringdefaultsetup.constant.ErrorCode.*;


/**
 * RestController Advice를 통하여
 * RestController를 사용하는 클래스들 즉, RestController.class의
 * 에러처리를 이곳에서 한번에 처리한다.
 *
 * @ControllerAdvice와 ResponseEntityExceptionHandler 모두 에러 핸들링 할 수 있다.
 * 다만, ResponseEntityExceptionHandler를 구현하고, @ControllerAdvice를 달아주게되면
 * Spring MVC 애플리케이션에서 발생하는 전반적인 예외(Global)를 처리할 수 있다.
 */
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalExceptionInternal(Exception e, WebRequest request){
        return handleExceptionInternal(e, INTERNAL_ERROR, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleGlobalExceptionNotFound(NotFoundException e, WebRequest request){
        return handleExceptionInternal(e, NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                e,
                APIErrorResponse.of(
                        false,
                        VALIDATION_ERROR.getCode(),
                        VALIDATION_ERROR.getMessage(e.getAllErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.joining(",")))
                ),
                HttpHeaders.EMPTY,
                VALIDATION_ERROR.getHttpStatus(),
                request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorCode errorCode, WebRequest request){
        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                errorCode.getHttpStatus(),
                request
        );
    }



}
