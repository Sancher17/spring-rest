package com.alex;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class User {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
}
