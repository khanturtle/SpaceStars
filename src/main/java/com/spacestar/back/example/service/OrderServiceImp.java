//package com.tyranno.ssg.order.application;
//
//
//import com.tyranno.ssg.cart.domain.Cart;
//import com.tyranno.ssg.cart.infrastructure.CartRepository;
//import com.tyranno.ssg.global.GlobalException;
//import com.tyranno.ssg.global.ResponseStatus;
//import com.tyranno.ssg.option.domain.Option;
//import com.tyranno.ssg.option.infrastructure.OptionRepository;
//import com.tyranno.ssg.order.domain.Order;
//import com.tyranno.ssg.order.domain.OrderList;
//import com.tyranno.ssg.order.dto.*;
//import com.tyranno.ssg.order.infrastructure.OrderListRepository;
//import com.tyranno.ssg.order.infrastructure.OrderRepository;
//
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import com.tyranno.ssg.product.infrastructure.DiscountRepository;
//import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
//import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.format.DateTimeFormatter;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class OrderServiceImp implements OrderService {
//
//    private final OrderListRepository orderListRepository;
//    private final OrderRepository orderRepository;
//    private final OptionRepository optionRepository;
//    private final VendorProductRepository vendorProductRepository;
//    private final ProductThumRepository productThumRepository;
//    private final DiscountRepository discountRepository;
//    private final CartRepository cartRepository;
//
//    @Override
//    @Transactional
//    public void addOrderList(OrderAddDto orderAddDto, String uuid) {
//        // 2. 주문번호 생성 ( 짜침 )
//        String createdOrderNumber = createOrderNumber(uuid);
//        // 3. 생성된 주문번호를 넣어줘서 orderList 완성
//        OrderList orderList = orderAddDto.toEntity(uuid, orderAddDto, createdOrderNumber);
//        // 4. 저장된 orderList Id (PK) 값을 가져오기 위해서 저장
//        OrderList savedOrderList = orderListRepository.save(orderList);
//
//        // 5. CartList 가져오기
//        List<Cart> CartList = cartRepository.findByUsersUuidAndIsKeep(uuid, (byte) 99);
//
//        /** 주문리스트 번호와 옵션아이디 넣어준다
//         *  개수와 가격도 넣어준다 stream으로
//         */
//        addOrder(savedOrderList, orderAddDto,CartList);
//
//    }
//    public void addDelivery(DeliveryAddDto deliveryAddDto, String uuid) {
//        Users users = usersRepository.findByUuid(uuid)
//                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
//        deliveryRepository.save(deliveryAddDto.toEntity(users));
//    }
//
//}
