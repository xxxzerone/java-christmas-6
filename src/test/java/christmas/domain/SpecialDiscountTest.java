package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {

    private Restaurant restaurant;
    private Map<Menu, Integer> menus;
    private SpecialDiscount specialDiscount;

    @BeforeEach
    void setUp() {
        menus = Map.of(
                Menu.T_BONE_STEAK, 1,
                Menu.CHOCOLATE_CAKE, 1,
                Menu.CAESAR_SALAD, 1,
                Menu.ICE_CREAM, 2
        );
        restaurant = new Restaurant(17, menus);
        specialDiscount = new SpecialDiscount(restaurant);
    }

    @Test
    @DisplayName("주말 특별 할인")
    void calculateDiscount_weekEnd() {
        Money money = specialDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("크리스마스 특별 할인")
    void calculateDiscount_christmas() {
        restaurant = new Restaurant(25, menus);
        specialDiscount = new SpecialDiscount(restaurant);

        Money money = specialDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("특별 할인 없음")
    void calculateDiscount_none() {
        restaurant = new Restaurant(19, menus);
        specialDiscount = new SpecialDiscount(restaurant);

        Money money = specialDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }
}