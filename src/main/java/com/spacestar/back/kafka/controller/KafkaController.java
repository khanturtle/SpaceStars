package com.spacestar.back.kafka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Alarm", description = "실시간 알림")
@RequestMapping("/api/v1/alarm")
public class KafkaController {

}
