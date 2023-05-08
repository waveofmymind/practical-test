package wave.practicaltest.spring.api.service.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductSellingStatus;
import wave.practicaltest.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateServiceRequest {

    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private int price;
    private String name;

    @Builder
    public ProductCreateServiceRequest(int price, ProductType type, ProductSellingStatus sellingStatus, String name) {
        this.price = price;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .name(name)
                .productNumber(nextProductNumber)
                .price(price)
                .sellingStatus(sellingStatus)
                .type(type)
                .build();
    }
}
