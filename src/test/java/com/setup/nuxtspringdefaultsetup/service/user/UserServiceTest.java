package com.setup.nuxtspringdefaultsetup.service.user;

import com.setup.nuxtspringdefaultsetup.domain.User;
import com.setup.nuxtspringdefaultsetup.dto.user.UserResponseDto;
import com.setup.nuxtspringdefaultsetup.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Slf4j
@DisplayName("business logic - User")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService sut;


    @DisplayName("유저Id를 이용해 유저를 검색하면, 결과를 보여준다.")
    @Test
    void givenUserId_whenSearchingUser_thenReturnUser(){
        // Given
        given(userRepository.findByUserId(anyLong()))
                .willReturn(createUser("alice"));

        // When
        UserResponseDto response = sut.getUser(anyLong());

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("userId", "alice")
                .hasFieldOrPropertyWithValue("name", "Alice")
                .hasFieldOrPropertyWithValue("email", "alice@gmail.com")
                .hasFieldOrPropertyWithValue("phoneNumber", "010-xxxx-xxxx")
                .hasFieldOrPropertyWithValue("age", 17)
                .hasFieldOrPropertyWithValue("nickname", "Alice!")
                .hasFieldOrPropertyWithValue("rank", 'K')
                .hasFieldOrPropertyWithValue("gender", 'G');

        then(userRepository).should(times(1)).findByUserId(anyLong());
    }

    private User createUser(String userId){
        return new User(
                1L,
                userId,
                "password1324",
                "Alice",
                "alice@gmail.com",
                "010-xxxx-xxxx",
                17,
                "Alice!",
                'K',
                'G',
                LocalDateTime.of(2022,10,17,17,0,0),
                LocalDateTime.of(2022,10,17,17,0,0)
        );
    }

}
