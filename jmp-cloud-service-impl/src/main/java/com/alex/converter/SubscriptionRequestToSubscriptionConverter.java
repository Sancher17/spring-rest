package com.alex.converter;

import com.alex.Subscription;
import com.alex.SubscriptionRequestDto;
import com.alex.User;
import com.alex.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionRequestToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        if (source == null) {
            return null;
        }
        return Subscription.builder()
                .id(source.getId())
                .startDate(LocalDate.parse(source.getStartDate()))
                .build();
    }
}
