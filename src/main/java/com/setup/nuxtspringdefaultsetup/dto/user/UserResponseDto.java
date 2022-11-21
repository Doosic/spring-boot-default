package com.setup.nuxtspringdefaultsetup.dto.user;

import com.setup.nuxtspringdefaultsetup.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String nickname;
    private Character rank;
    private Character gender;

    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .nickname(user.getNickname())
                .rank(user.getRank())
                .gender(user.getGender())
                .build();
    }
}
