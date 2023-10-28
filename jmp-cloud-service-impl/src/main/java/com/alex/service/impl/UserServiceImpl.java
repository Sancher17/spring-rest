package com.alex.service.impl;

import com.alex.User;
import com.alex.UserRequestDto;
import com.alex.UserResponseDto;
import com.alex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private AtomicLong userId = new AtomicLong(0L);
    private Set<User> users = new HashSet<>();
    private final ConversionService conversionService;

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        Objects.requireNonNull(user).setId(userId.incrementAndGet());
        users.add(user);
        return conversionService.convert(user, UserResponseDto.class);
    }

    public UserResponseDto update(UserRequestDto userRequestDto, Long id) {
        User user = getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateUser(userRequestDto, user);
        return conversionService.convert(user, UserResponseDto.class);
    }

    public void delete(Long id) {
        getUserById(id).ifPresent(users::remove);
    }

    public UserResponseDto getById(Long id) {
        User user = getUserById(id).orElse(null);
        return conversionService.convert(user, UserResponseDto.class);
    }

    public List<UserResponseDto> getAll() {
        TypeDescriptor sourceType = TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(User.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserResponseDto.class));
        return (List<UserResponseDto>) conversionService.convert(users, sourceType, targetType);
    }

    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    private void updateUser(UserRequestDto userRequestDto, User user) {
            user.setName(userRequestDto.getName());
            user.setSurname(userRequestDto.getSurname());
            user.setBirthday(LocalDate.parse(userRequestDto.getBirthday()));
    }
}
