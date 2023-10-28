package com.alex;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDto {

   private Long id;
   private String name;
   private String surname;
   private String birthday;
}
