package com.alex.service;

import com.alex.User;
import com.alex.UserRequestDto;
import com.alex.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto update(UserRequestDto userRequestDto, Long id);void delete(Long id);
    UserResponseDto getById(Long id);
    Optional<User> getUserById(Long id);
    List<UserResponseDto> getAll();
}