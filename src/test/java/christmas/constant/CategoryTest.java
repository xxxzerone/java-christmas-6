package christmas.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    private Map<Menu, Integer> menus;

    @BeforeEach
    void setUp() {
        menus = new EnumMap<Menu, Integer>(Menu.class);

        menus.put(Menu.CHRISTMAS_PASTA, 1);
        menus.put(Menu.PORK_RIBS, 1);
        menus.put(Menu.CHOCOLATE_CAKE, 1);
        menus.put(Menu.ICE_CREAM, 2);
    }

    @Test
    @DisplayName("디저트 개수 확인")
    void findByDessert() {
        int dessertCount = Category.findByDessertCount(menus);

        assertThat(dessertCount).isEqualTo(3);
    }

    @Test
    @DisplayName("다른 음식이 있어서 false")
    void isOnlyDrink_false() {
        boolean onlyDrink = Category.isOnlyDrink(menus.keySet());

        assertThat(onlyDrink).isFalse();
    }

    @Test
    @DisplayName("음료만 있을 경우 true")
    void isOnlyDrink_true() {
        menus = new EnumMap<Menu, Integer>(Menu.class);
        menus.put(Menu.COKE_ZERO, 1);
        menus.put(Menu.RED_WINE, 2);

        boolean onlyDrink = Category.isOnlyDrink(menus.keySet());

        assertThat(onlyDrink).isTrue();
    }
}