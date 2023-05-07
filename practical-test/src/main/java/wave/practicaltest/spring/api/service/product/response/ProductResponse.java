package wave.practicaltest.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductSellingStatus;
import wave.practicaltest.spring.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;

    private String productNumber;

    private ProductType type;

    private ProductSellingStatus sellingStatus;

    private String name;

    private int price;

    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productNumber(product.getProductNumber())
                .sellingStatus(product.getSellingStatus())
                .type(product.getType()).build();
    }
}
