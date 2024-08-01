package com.spacestar.back.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.kafka.message.MatchingMessage;
import com.spacestar.back.kafka.service.KafkaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/swipe")
@RequiredArgsConstructor
public class KafkaController {

}
