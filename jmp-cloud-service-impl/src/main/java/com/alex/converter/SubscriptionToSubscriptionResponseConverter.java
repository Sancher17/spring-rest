package com.alex.converter;

import com.alex.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionResponseConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        if (source == null) {
            return null;
        }
        return SubscriptionResponseDto.builder()
                .id(source.getId())
                .userId(source.getUser() != null ? source.getUser().getId() : null)
                .startDate(source.getStartDate().toString())
                .build();

    }
}
