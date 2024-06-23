package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {

    private Map<Menu, Integer> menus = new EnumMap<Menu, Integer>(Menu.class);
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        menus.put(Menu.T_BONE_STEAK, 1);
        menus.put(Menu.PORK_RIBS, 1);
        menus.put(Menu.CHOCOLATE_CAKE, 2);
        menus.put(Menu.COKE_ZERO, 1);
        restaurant = new Restaurant(3, menus);
    }

    @Test
    @DisplayName("할인 전 총주문 금액")
    void calculateTotalPriceBeforeDiscount() {
        int totalPrice = restaurant.calculateTotalPriceBeforeDiscount();

        assertThat(totalPrice).isEqualTo(142000);
    }

    @Test
    @DisplayName("총혜택 금액")
    void calculateTotalDiscount() {
        int totalDiscount = restaurant.calculateTotalDiscount();

        assertThat(totalDiscount).isEqualTo(31246);
    }

    @Test
    @DisplayName("할인 후 예상 금액")
    void calculateTotalPriceAfterDiscount() {
        int totalAmount = restaurant.calculateTotalPriceAfterDiscount();

        assertThat(totalAmount).isEqualTo(135754);
    }
}