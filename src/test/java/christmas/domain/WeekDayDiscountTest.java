package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekDayDiscountTest {

    private Restaurant restaurant;
    private Map<Menu, Integer> menus;
    private WeekDayDiscount weekDayDiscount;

    @BeforeEach
    void setUp() {
        menus = Map.of(
                Menu.T_BONE_STEAK, 1,
                Menu.CHOCOLATE_CAKE, 1,
                Menu.CAESAR_SALAD, 1,
                Menu.ICE_CREAM, 2
        );
        restaurant = new Restaurant(7, menus);
        weekDayDiscount = new WeekDayDiscount(restaurant);
    }

    @Test
    @DisplayName("평일 디저트 개수 3개 할인")
    void calculateDiscount_three() {
        Money money = weekDayDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(2023 * 3);
    }

    @Test
    @DisplayName("주말 할인 없음")
    void calculateDiscount_none() {
        restaurant = new Restaurant(9, menus);
        weekDayDiscount = new WeekDayDiscount(restaurant);

        Money money = weekDayDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("평일 디저트 0개 할인 없음")
    void calculateDiscount_no_discount() {
        menus = Map.of(Menu.T_BONE_STEAK, 1, Menu.CAESAR_SALAD, 1);
        restaurant = new Restaurant(7, menus);
        weekDayDiscount = new WeekDayDiscount(restaurant);

        Money money = weekDayDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }
}