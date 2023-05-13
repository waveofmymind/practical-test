package wave.practicaltest.spring.api.service.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wave.practicaltest.spring.api.controller.product.dto.request.ProductCreateRequest;
import wave.practicaltest.spring.api.service.product.response.ProductResponse;
import wave.practicaltest.spring.domain.product.Product;
import wave.practicaltest.spring.domain.product.ProductRepository;
import wave.practicaltest.spring.domain.product.ProductSellingStatus;
import wave.practicaltest.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static wave.practicaltest.spring.domain.product.ProductSellingStatus.*;
import static wave.practicaltest.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() {
        //각 테스트 입장에서 봤을 때 : 아예 몰라도 테스트 내용을 이해하는데 문제가 없는가?
        //수정해도 모든 테스트에 영향을 주지 않는가?
    }

    @DisplayName("신규 상품을 등록한다. 상품 번호는 가장 최근 상품의 상품 번호에서 1 증가한 값이다.")
    @Test
    void createProduct() {
        //given
        Product product = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();


        //when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then
        assertThat(productResponse)
                .extracting("productNumber","type","sellingStatus","price","name")
                .contains("002",HANDMADE,SELLING,5000,"카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber","type","sellingStatus","price","name")
                .containsExactlyInAnyOrder(
                    tuple("001",HANDMADE,SELLING,4000,"아메리카노"),
                    tuple("002",HANDMADE,SELLING,5000,"카푸치노")
                );

    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품 번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty () {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        //when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then
        assertThat(productResponse)
                .extracting("productNumber","type","sellingStatus","price","name")
                .contains("001",HANDMADE,SELLING,5000,"카푸치노");
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price).build();
    }
}