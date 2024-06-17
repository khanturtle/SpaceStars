package com.spacestar.back.auth.dto.res;

import com.spacestar.back.auth.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuickAuthInfoResDto {

    private int age;
    private GenderType gender;

    public static QuickAuthInfoResDto converter(int age, GenderType gender){
        return QuickAuthInfoResDto.builder()
                .age(age)
                .gender(gender)
                .build();
    }
}
