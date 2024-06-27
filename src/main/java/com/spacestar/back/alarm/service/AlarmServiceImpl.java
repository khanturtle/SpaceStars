package com.spacestar.back.alarm.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.dto.req.AlarmDeleteReqDto;
import com.spacestar.back.alarm.dto.res.AlarmListResDto;
import com.spacestar.back.alarm.dto.res.AlarmResDto;
import com.spacestar.back.alarm.dto.res.AlarmStateResDto;
import com.spacestar.back.alarm.repository.AlarmMongoRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.kafka.message.FriendMessage;
import com.spacestar.back.kafka.message.MatchingMessage;
import com.spacestar.back.kafka.message.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

	private final AlarmMongoRepository alarmRepository;
	private final Sinks.Many<MatchingMessage> matchingSink;
	private final Sinks.Many<FriendMessage> friendSink;
	private final ConcurrentHashMap<String, Boolean> connectedUuids = new ConcurrentHashMap<>();

	// SSE연결
	@Override
	public Flux<Message> connectToSse(String uuid){
		return handlesSseConnection(uuid);
	}

	// 연결상태 확인
	private Flux<Message> handlesSseConnection(String uuid){
		if(connectedUuids.putIfAbsent(uuid, Boolean.TRUE) != null){
			log.info("UUID {} is already connected", uuid);
			return Flux.error(new GlobalException(ResponseStatus.DUPLICATE_CONNECTION));
		}
		log.info("연결시도 : {}", uuid);
		return streamAlarms(uuid)
			.doFinally(signalType -> connectedUuids.remove(uuid));
	}

	// 알림 전송
	private Flux<Message> streamAlarms(String uuid) {
		return Flux.merge(
			matchingSink.asFlux().filter(message -> uuid.equals(message.getReceiverUuid())),
			friendSink.asFlux().filter(message -> uuid.equals(message.getReceiverUuid()))
		).doOnNext(message -> {
			log.info("Received message for UUID {}: {}", uuid, message);
		});
	}

	@Override
	public void addAlarm(String uuid, AlarmAddReqDto alarmAddReqDto) {
		alarmRepository.save(AlarmAddReqDto.toEntity(uuid, alarmAddReqDto));
	}

	@Override
	public AlarmListResDto getAlarmList(String uuid) {

		List<Alarm> alarms = alarmRepository.findByReceiverUuid(uuid);
		return AlarmListResDto.builder()
			.alarmList(IntStream.range(0, alarms.size())
				.mapToObj(i -> AlarmResDto.builder()
					.index(i)
					.senderUuid(alarms.get(i).getSenderUuid())
					.checkStatus(alarms.get(i).getCheckStatus())
					.alarmType(alarms.get(i).getAlarmType())
					.content(alarms.get(i).getContent())
					.build()).toList())
			.build();
	}

	@Override
	public AlarmStateResDto getAlarmState(String uuid, String id) {
		Alarm alarm = alarmRepository.findByReceiverUuidAndId(uuid, id)
			.orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_ALARM));

		return AlarmStateResDto.builder()
			.checkStatus(alarm.getCheckStatus())
			.build();
	}

	@Override
	public void modifyAlarmRead(String alarmId, String uuid) {
		// 변경사항이 없을 경우 : 알림이 존재하지 않거나 && 알림이 이미 읽은 상태인 경우
		if (alarmRepository.modifyAlarm(alarmId, uuid).getModifiedCount() == 0) {
			throw new GlobalException(ResponseStatus.NOT_MODIFIED_ALARM);
		}
	}

	@Override
	public void deleteAlarm(String uuid, AlarmDeleteReqDto alarmDeleteReqDto) {

		if (alarmRepository.deleteManyAlarm(uuid, alarmDeleteReqDto).getDeletedCount() == 0) {
			throw new GlobalException(ResponseStatus.NOT_DELETE_ALARM);
		}
	}
}
