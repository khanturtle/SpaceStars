package com.spacestar.back.alarm.vo.res;

import com.spacestar.back.alarm.dto.res.AlarmStateResDto;
import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmStateResVo {

	private CheckStatus checkStatus;
}
