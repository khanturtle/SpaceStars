package com.spacestar.back.global;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
public abstract class GlobalCreateTime {

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

}
