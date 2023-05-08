package wave.practicaltest.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wave.practicaltest.spring.api.controller.order.request.OrderCreateRequest;
import wave.practicaltest.spring.api.service.order.response.OrderResponse;
import wave.practicaltest.spring.domain.order.Order;
import wave.practicaltest.spring.domain.order.OrderRepository;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();

        List<Product> products = findProductsBy(productNumbers);

        Order order = Order.create(products,registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, product -> product));

        return  productNumbers.stream()
                .map(productMap::get)
                .toList();
    }
}
