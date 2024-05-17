package com.spacestar.back.member.dto.req;

import com.spacestar.back.member.enums.GenderType;
import com.spacestar.back.member.vo.req.MemberJoinReqVo;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinReqDto {

    private String email;
    private String nickname;
    private String imageUrl;
    private GenderType gender;
    private LocalDate birth;
    private Boolean infoAgree;

}
