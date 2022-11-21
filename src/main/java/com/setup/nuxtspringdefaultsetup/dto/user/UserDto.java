package com.setup.nuxtspringdefaultsetup.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String nickname;
    private Character rank;
    private Character gender;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;




}
