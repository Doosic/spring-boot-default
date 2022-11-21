package com.setup.nuxtspringdefaultsetup.common;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class APIErrorResponse {

    private final Boolean success;
    private final Integer errorCode;
    private final String message;

    public static APIErrorResponse of(Boolean success, Integer errorCode, String message){
        return new APIErrorResponse(success, errorCode, message);
    }


}
