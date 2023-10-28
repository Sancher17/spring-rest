package com.alex.service;

import com.alex.SubscriptionRequestDto;
import com.alex.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto update(SubscriptionRequestDto subscriptionRequestDto, Long id);

    void delete(Long id);

    SubscriptionResponseDto getById(Long id);

    List<SubscriptionResponseDto> getAll();
}