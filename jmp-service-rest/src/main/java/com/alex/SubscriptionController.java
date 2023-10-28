package com.alex;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionController {

    ResponseEntity<SubscriptionResponseDto> createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    ResponseEntity<SubscriptionResponseDto> updateSubscription(SubscriptionRequestDto subscriptionRequestDto, Long id);

    ResponseEntity<Long> deleteSubscription(Long id);

    ResponseEntity<SubscriptionResponseDto> getSubscription(Long id);

    ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription();

}