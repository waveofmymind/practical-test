package wave.practicaltest.spring.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import wave.practicaltest.spring.IntegrationTestSupport;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductRepository;
import wave.practicaltest.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static wave.practicaltest.spring.domain.product.ProductSellingStatus.SELLING;


class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("결제 완료된 주문 조회시 특정 시간 이내여야한다.")
    @Test
    void findOrderBy() {
        //given
        LocalDateTime registeredDateTime = LocalDateTime.of(2023, 9, 13, 2, 29);
        LocalDateTime startDateTime = LocalDateTime.of(2023, 9, 12, 1, 29);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 9, 13, 2, 30);

        Product product = createProduct(ProductType.HANDMADE, "001", 1000);
        Product savedProduct = productRepository.save(product);
        Order order = Order.create(List.of(savedProduct),registeredDateTime);

        orderRepository.save(order);
        //when
        List<Order> orders = orderRepository.findOrdersBy(startDateTime, endDateTime, OrderStatus.INIT);
        //then
        Assertions.assertThat(orders.get(0).getRegisteredDateTime()).isBefore(endDateTime);
        Assertions.assertThat(orders.get(0).getRegisteredDateTime()).isAfter(startDateTime);
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}