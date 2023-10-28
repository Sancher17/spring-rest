package com.alex;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Subscription {

    private Long id;
    private User user;
    private LocalDate startDate;
}
