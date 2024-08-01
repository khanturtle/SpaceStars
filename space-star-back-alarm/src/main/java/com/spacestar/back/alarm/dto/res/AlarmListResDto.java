package com.spacestar.back.alarm.dto.res;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AlarmListResDto {

	List<AlarmResDto> alarmList;
}
