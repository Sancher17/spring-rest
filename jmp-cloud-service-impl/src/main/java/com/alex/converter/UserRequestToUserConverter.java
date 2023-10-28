package com.alex.converter;

import com.alex.User;
import com.alex.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRequestToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto source) {
        if (source == null) {
            return null;
        }
        return User.builder()
                .name(source.getName())
                .surname(source.getSurname())
                .birthday(LocalDate.parse(source.getBirthday()))
                .build();
    }
}
