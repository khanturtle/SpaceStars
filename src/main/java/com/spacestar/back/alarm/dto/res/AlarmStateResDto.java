package com.spacestar.back.alarm.dto.res;

import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AlarmStateResDto {

	private CheckStatus checkStatus;
}
