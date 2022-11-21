package com.setup.nuxtspringdefaultsetup.common;

import com.setup.nuxtspringdefaultsetup.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class APIDataResponseTest {

    @DisplayName("객체형 데이터가 주어지면, 표준 응답을 생성한다.")
    @Test
    void givenObjectData_whenCreatingResponse_thenReturnSuccessfulResponse(){
        // Given
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Alice");
        data.put("age", 17);
        data.put("phone", "010-XXXX-XXXX");

        // When
        APIDataResponse<Object> response = APIDataResponse.of(data);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("data", data);
    }

    @DisplayName("데이터가 없을때, 데이터가 비어있는 표준 응답을 생성한다.")
    @Test
    void givenNothing_whenCreatingResponse_thenReturnSuccessfulResponse(){
        // Given

        // When
        APIDataResponse<Object> response = APIDataResponse.of(null);

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("data", null);
    }

}
