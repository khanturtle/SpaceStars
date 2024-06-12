package com.spacestar.back.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spacestar.back.alarm.domain.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, CustomAlarmRepository {
}
