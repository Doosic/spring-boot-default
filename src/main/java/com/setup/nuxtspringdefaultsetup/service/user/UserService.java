package com.setup.nuxtspringdefaultsetup.service.user;

import com.setup.nuxtspringdefaultsetup.dto.user.UserResponseDto;
import com.setup.nuxtspringdefaultsetup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> getUsers(){
        return userRepository.findAll().stream()
                        .map(UserResponseDto::from)
                        .collect(Collectors.toList());
    }

    public UserResponseDto getUser(Long userId){
        return UserResponseDto.from(userRepository.findByUserId(userId));
    }
}
