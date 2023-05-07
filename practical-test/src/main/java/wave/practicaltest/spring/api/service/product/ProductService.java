package wave.practicaltest.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wave.practicaltest.spring.api.service.product.response.ProductResponse;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductRepository;
import wave.practicaltest.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> productList = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return productList.stream().map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
