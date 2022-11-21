package com.setup.nuxtspringdefaultsetup.exception;

import com.setup.nuxtspringdefaultsetup.common.APIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import static com.setup.nuxtspringdefaultsetup.constant.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


@Slf4j
class APIExceptionHandlerTest {

    private APIExceptionHandler sut;

    private WebRequest webRequest;


    @BeforeEach
    void setUp() {
        sut = new APIExceptionHandler();
        webRequest = new DispatcherServletWebRequest(new MockHttpServletRequest());
    }


    @DisplayName("INTERNAL_ERROR - 응답 데이터 정의")
    @Test
    void givenInternalError_whenCallingException_thenReturnResponseEntity(){
        // Given
        Exception e = new Exception();

        // When
        ResponseEntity<Object> response = sut.handleGlobalExceptionInternal(e, webRequest);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, INTERNAL_ERROR.getCode(), INTERNAL_ERROR.getMessage(e)))
                .hasFieldOrPropertyWithValue("status", INTERNAL_ERROR.getHttpStatus())
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY);
    }

    @DisplayName("NOT_FOUND - 응답 데이터 정의")
    @Test
    void givenNotFoundError_whenCallingException_thenReturnResponseEntity(){
        // Given
        ChangeSetPersister.NotFoundException e = new ChangeSetPersister.NotFoundException();

        // When
        ResponseEntity<Object> response = sut.handleGlobalExceptionNotFound(e, webRequest);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, NOT_FOUND.getCode(), NOT_FOUND.getMessage(e)))
                .hasFieldOrPropertyWithValue("status", NOT_FOUND.getHttpStatus())
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY);
    }

    @DisplayName("VALIDATION_ERROR - 응답 데이터 정의")
    @Test
    void givenValidationError_whenCallingException_thenReturnResponseEntity(){
        // Given
        MethodArgumentNotValidException e = mock(MethodArgumentNotValidException.class);

        // When
        ResponseEntity<Object> response = sut.handleMethodArgumentNotValid(e, HttpHeaders.EMPTY, VALIDATION_ERROR.getHttpStatus(), webRequest);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getMessage(e)))
                .hasFieldOrPropertyWithValue("status", VALIDATION_ERROR.getHttpStatus())
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY);
    }


}
