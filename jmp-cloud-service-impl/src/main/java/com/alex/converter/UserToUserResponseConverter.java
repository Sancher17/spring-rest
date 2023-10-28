package com.alex.converter;

import com.alex.User;
import com.alex.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User source) {
        if (source == null) {
            return null;
        }
        return UserResponseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .surname(source.getSurname())
                .birthday(source.getBirthday().toString())
                .build();

    }
}
