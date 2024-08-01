package com.spacestar.back.auth.jwt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String role;
    private String uuid;

}
