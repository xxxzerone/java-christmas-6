package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekEndDiscountTest {

    private Restaurant restaurant;
    private Map<Menu, Integer> menus;
    private WeekEndDiscount weekEndDiscount;

    @BeforeEach
    void setUp() {
        menus = Map.of(
                Menu.T_BONE_STEAK, 1,
                Menu.CHOCOLATE_CAKE, 1,
                Menu.SEAFOOD_PASTA, 1,
                Menu.ICE_CREAM, 2
        );
        restaurant = new Restaurant(16, menus);
        weekEndDiscount = new WeekEndDiscount(restaurant);
    }

    @Test
    @DisplayName("주말 메인 개수 2개 할인")
    void calculateDiscount_two() {
        Money money = weekEndDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(2023 * 2);
    }

    @Test
    @DisplayName("평일 할인 없음")
    void calculateDiscount_none() {
        restaurant = new Restaurant(13, menus);
        weekEndDiscount = new WeekEndDiscount(restaurant);

        Money money = weekEndDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("주말 메인 0개 할인 없음")
    void calculateDiscount_no_discount() {
        menus = Map.of(Menu.MUSHROOM_CREAM_SOUP, 1, Menu.CAESAR_SALAD, 1);
        restaurant = new Restaurant(23, menus);
        weekEndDiscount = new WeekEndDiscount(restaurant);

        Money money = weekEndDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }
}