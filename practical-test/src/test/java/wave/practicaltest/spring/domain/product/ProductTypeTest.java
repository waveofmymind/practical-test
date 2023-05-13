package wave.practicaltest.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType() {
        //given
        ProductType givenType = ProductType.HANDMADE;
        //when
        boolean result = ProductType.containsStockType(givenType);
        //then

        Assertions.assertThat(result).isFalse();

    }
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType2() {
        //given
        ProductType givenType = ProductType.BAKERY;
        //when
        boolean result = ProductType.containsStockType(givenType);
        //then

        Assertions.assertThat(result).isTrue();

    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType3() {
        //given
        ProductType givenType1= ProductType.BAKERY;
        ProductType givenType2 = ProductType.BOTTLE;
        ProductType givenType3 = ProductType.HANDMADE;
        //when
        boolean result1 = ProductType.containsStockType(givenType1);
        boolean result2 = ProductType.containsStockType(givenType2);
        boolean result3 = ProductType.containsStockType(givenType3);
        //then

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isFalse();

    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"BAKERY, true", "BOTTLE, true", "HANDMADE, false"})
    @ParameterizedTest
    void containsStockType4(ProductType productType,boolean expected) {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then

        Assertions.assertThat(result).isEqualTo(expected);

    }




}