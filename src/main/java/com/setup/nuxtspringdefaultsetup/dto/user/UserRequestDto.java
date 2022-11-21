package com.setup.nuxtspringdefaultsetup.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String userId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String nickname;
    private Character rank;
    private Character gender;

    public UserDto toDto(){
        return UserDto.builder()
                .id(null)
                .userId(this.userId)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .age(this.age)
                .nickname(this.nickname)
                .rank(this.rank)
                .gender(this.gender)
                .build();
    }

}
