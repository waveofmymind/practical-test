package wave.practicaltest.spring.api.controller.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wave.practicaltest.spring.api.service.product.request.ProductCreateServiceRequest;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductSellingStatus;
import wave.practicaltest.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {
    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;
    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;
    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;
    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Builder
    public ProductCreateRequest(int price, ProductType type, ProductSellingStatus sellingStatus, String name) {
        this.price = price;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
    }

    public ProductCreateServiceRequest toServiceRequest() {
        return ProductCreateServiceRequest.builder()
                .price(price)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name).build();
    }
}
