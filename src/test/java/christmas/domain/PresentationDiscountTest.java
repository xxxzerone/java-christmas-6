package christmas.domain;

import christmas.constant.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationDiscountTest {

    private Restaurant restaurant;
    private Map<Menu, Integer> menus = new EnumMap<Menu, Integer>(Menu.class);
    private PresentationDiscount presentationDiscount;

    @BeforeEach
    void setUp() {
        menus.put(Menu.T_BONE_STEAK, 1);
        menus.put(Menu.COKE_ZERO, 1);
        restaurant = new Restaurant(12, menus);
        presentationDiscount = new PresentationDiscount(restaurant);
    }

    @AfterEach
    void tearDown() {
        menus.clear();
    }

    @Test
    @DisplayName("증정 이벤트 존재")
    void calculateDiscount_ok() {
        menus.put(Menu.PORK_RIBS, 1);
        menus.put(Menu.CHOCOLATE_CAKE, 2);
        restaurant = new Restaurant(12, menus);
        presentationDiscount = new PresentationDiscount(restaurant);

        Money money = presentationDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(25000);
    }

    @Test
    @DisplayName("증정 이벤트 없음")
    void calculateDiscount_none() {
        Money money = presentationDiscount.getAmount();

        assertThat(money.getAmount()).isEqualTo(0);
    }
}