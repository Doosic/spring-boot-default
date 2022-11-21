package com.setup.nuxtspringdefaultsetup.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ErrorCodeTest {


    @ParameterizedTest
    @MethodSource
    @DisplayName("예외를 받으면, 예외 코드와 예외 메시지가 포함된 메시지를 출력")
    void givenExceptionWithMessage_whenGettingMessage_thenReturnsMessage(ErrorCode sut, String expected){
        // Given
        Exception e = new Exception("This is test Exception message.");

        // When
        String result = sut.getMessage(e);

        // Then
        assertThat(result).isEqualTo(expected);
        System.out.println(result);
    }

    static Stream<Arguments> givenExceptionWithMessage_whenGettingMessage_thenReturnsMessage(){
        return Stream.of(
                arguments(ErrorCode.OK, "Ok - This is test Exception message."),
                arguments(ErrorCode.NOT_FOUND, "Request resource is not found - This is test Exception message."),
                arguments(ErrorCode.VALIDATION_ERROR, "Validation error - This is test Exception message."),
                arguments(ErrorCode.INTERNAL_ERROR, "Internal error - This is test Exception message."),
                arguments(ErrorCode.DATA_ACCESS_ERROR, "Data access error - This is test Exception message.")
        );
    }


    @ParameterizedTest
    @MethodSource
    @DisplayName("직접 메세지를 받으면, 해당 에러 메세지로 출력")
    void givenMessage_whenGettingMessage_thenReturnsMessage(String input, String expected){
        // Given

        // When
        String result = ErrorCode.INTERNAL_ERROR.getMessage(input);

        // Then
        assertThat(result).isEqualTo(expected);
        System.out.println(result);
    }

    static Stream<Arguments> givenMessage_whenGettingMessage_thenReturnsMessage(){
        return Stream.of(
                arguments(null, ErrorCode.INTERNAL_ERROR.getMessage()),
                arguments("", ErrorCode.INTERNAL_ERROR.getMessage()),
                arguments(" ", ErrorCode.INTERNAL_ERROR.getMessage()),
                arguments("This is test Error message", "This is test Error message")
        );
    }
}
