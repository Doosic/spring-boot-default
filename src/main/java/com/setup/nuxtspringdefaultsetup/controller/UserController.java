package com.setup.nuxtspringdefaultsetup.controller;

import com.setup.nuxtspringdefaultsetup.common.APIDataResponse;
import com.setup.nuxtspringdefaultsetup.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public APIDataResponse getUsers(
    ){
        return APIDataResponse.of(userService.getUsers());
    }

    @GetMapping("/users/{userId}")
    public APIDataResponse getUser(
            @PathVariable Long userId
    ){
        return APIDataResponse.of(userService.getUser(userId));
    }

}
