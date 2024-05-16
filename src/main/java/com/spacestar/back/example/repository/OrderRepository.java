//package com.tyranno.ssg.order.infrastructure;
//
//import com.tyranno.ssg.order.domain.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface OrderRepository extends JpaRepository<Order, Long> {
//
//    List<Order> findAllByOrderListId(Long orderListId);
//
//    List<Order> findByOrderListIdAndIsReview(Long orderListId, int i);
//}
