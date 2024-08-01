package com.spacestar.back.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {

    private String uuid;
    private boolean isExist;

    @Override
    public String toString() {
        return "ProfileDto{" +
                "uuid='" + uuid + '\'' +
                ", isExist= '" + isExist +
                '}';
    }
}
