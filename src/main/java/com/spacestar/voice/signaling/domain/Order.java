//package com.tyranno.ssg.order.domain;
//
//import com.tyranno.ssg.option.domain.Option;
//import jakarta.persistence.*;
//import lombok.*;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Getter
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Option option;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private OrderList orderList;
//
//    @Column(nullable = false)
//    private Integer count;
//
//    @Column(nullable = false)
//    private Integer money;
//
//    @Column(nullable = false)
//    private Byte isReview;
//
//
//}
