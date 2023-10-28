package com.alex;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubscriptionResponseDto {

  private Long id;
  private Long userId;
  private String startDate;
}
