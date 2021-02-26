package springeighthproject.spring_jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springeighthproject.spring_jpa.domain.Order;
import springeighthproject.spring_jpa.repository.OrderRepository;
import springeighthproject.spring_jpa.repository.OrderSearch;
import springeighthproject.spring_jpa.service.OrderService;

import java.util.List;

/*
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderService.findOrders(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // LAZY 강제 초기화
            order.getDelivery().getAddress();
        }
        return all;
    }

}
