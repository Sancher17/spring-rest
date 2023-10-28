package com.alex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto, Long id);

    ResponseEntity<Long> deleteUser(Long id);

    ResponseEntity<UserResponseDto> getUser(Long id);

    ResponseEntity<List<UserResponseDto>> getAllUser();
}
