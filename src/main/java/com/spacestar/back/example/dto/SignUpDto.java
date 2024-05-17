package com.spacestar.back.example.dto;


import com.spacestar.back.config.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @NotBlank(message = "아이디를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "아이디는 영어 또는 숫자로 6 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
            @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String name;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryBase;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryDetail;

    @NotNull
    private int zipCode;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$",
            message = "휴대폰 번호 형식이 올바르지 않습니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String phoneNumber;

    @NotBlank(message = "이메일을 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "올바르지 않은 이메일 형식입니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String email;

    @NotNull
    private Byte gender;

    @NotNull
    private LocalDate birth;

}
