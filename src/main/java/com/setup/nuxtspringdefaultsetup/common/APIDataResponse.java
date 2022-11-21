package com.setup.nuxtspringdefaultsetup.common;

import com.setup.nuxtspringdefaultsetup.constant.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class APIDataResponse<T> extends APIErrorResponse{

    private final T data;

    protected APIDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    /*
        - of 메서드를 만들어 생성자를 Return 해주는 이유
        생성자에 인수값으로 바로 데이터를 리턴해주기 보다는 of 라는 메서드를 사용하여
        APIDataResponse of data 즉, APIDataResponse의 data라고 확실히 표기해주기 위함이다.
    */
    public static <T> APIDataResponse<T> of (T data){
        return new APIDataResponse(data);
    }
}
