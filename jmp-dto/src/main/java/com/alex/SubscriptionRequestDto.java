package com.alex;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubscriptionRequestDto {

   private Long id;
   private Long userId;
   private String startDate;
}
