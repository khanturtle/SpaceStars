//package com.spacestar.back.example.controller;
//
//import com.tyranno.ssg.global.ResponseEntity;
//import com.tyranno.ssg.order.application.OrderService;
//import com.tyranno.ssg.order.dto.OrderAddDto;
//import com.tyranno.ssg.order.dto.OrderListDto;
//import com.tyranno.ssg.order.dto.ResponseNonOrderDto;
//import com.tyranno.ssg.security.JwtTokenProvider;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Random;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/order")
//@Slf4j
//public class OrderController {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final OrderService orderService;
//
//    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회한다.")
//    @GetMapping
//    public ResponseEntity<List<OrderListDto>> getOrderList(@RequestHeader(value = "Authorization",required = false) String token) {
//        String uuid = jwtTokenProvider.tokenToUuid(token);
//        // 주문내역 없을경우 에러처리
//        if (uuid == null) {
//            return new ResponseEntity<>(null);
//        }
//        List<OrderListDto> orderListDto = orderService.getOrderList(uuid);
//
//        return new ResponseEntity<>(orderListDto);
//    }
//
//
//
//
//}
