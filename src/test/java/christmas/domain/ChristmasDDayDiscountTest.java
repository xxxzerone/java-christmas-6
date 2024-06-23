package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayDiscountTest {

    private Restaurant restaurant;
    private Map<Menu, Integer> menus;
    private ChristmasDDayDiscount christmasDDayDiscount;

    @BeforeEach
    void setUp() {
        menus = Map.of(Menu.T_BONE_STEAK, 1);
        restaurant = new Restaurant(25, menus);
        christmasDDayDiscount = new ChristmasDDayDiscount(restaurant);
    }

    @Test
    @DisplayName("3400원 크리스마스 디데이 할인")
    void calculateDiscount() {
        Money money = christmasDDayDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(3400);
    }

    @Test
    @DisplayName("1000원 크리스마스 디데이 할인")
    void calculateDiscount2() {
        restaurant = new Restaurant(1, menus);
        christmasDDayDiscount = new ChristmasDDayDiscount(restaurant);
        Money money = christmasDDayDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(1000);
    }
}