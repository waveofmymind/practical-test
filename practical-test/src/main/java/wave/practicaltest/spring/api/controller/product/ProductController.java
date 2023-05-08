package wave.practicaltest.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wave.practicaltest.spring.api.controller.product.dto.request.ProductCreateRequest;
import wave.practicaltest.spring.api.service.product.ProductService;
import wave.practicaltest.spring.api.service.product.response.ProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/new")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
        productService.createProduct(request);
        return null;
    }

    @GetMapping("/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }


}
