package com.alex.service.impl;

import com.alex.Subscription;
import com.alex.SubscriptionRequestDto;
import com.alex.SubscriptionResponseDto;
import com.alex.service.SubscriptionService;
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
public class SubscriptionServiceImpl implements SubscriptionService {

    private AtomicLong subscriptionId = new AtomicLong(0L);
    private Set<Subscription> subscriptions = new HashSet<>();
    private final ConversionService conversionService;
    private final UserService userService;

    @Override
    public SubscriptionResponseDto create(SubscriptionRequestDto requestDto) {
        Subscription subscription = conversionService.convert(requestDto, Subscription.class);
        subscription.setUser(userService.getUserById(requestDto.getUserId()).orElse(null));
        Objects.requireNonNull(subscription).setId(subscriptionId.incrementAndGet());
        subscriptions.add(subscription);
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public SubscriptionResponseDto update(SubscriptionRequestDto requestDto, Long id) {
        Subscription subscription = getSubscriptionById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateSubscription(requestDto, subscription);
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        getSubscriptionById(id).ifPresent(subscriptions::remove);
    }

    @Override
    public SubscriptionResponseDto getById(Long id) {
        Subscription subscription = getSubscriptionById(id).orElse(null);
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        TypeDescriptor sourceType = TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Subscription.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(SubscriptionResponseDto.class));
        return (List<SubscriptionResponseDto>) conversionService.convert(subscriptions, sourceType, targetType);
    }

    private Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptions.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    private void updateSubscription(SubscriptionRequestDto requestDto, Subscription subscription) {
           subscription.setStartDate(LocalDate.parse(requestDto.getStartDate()));
           userService.getUserById(requestDto.getUserId())
                    .ifPresent(subscription::setUser);
    }
}
