package christmas.util;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;

class ConvertTest {
    Map<String, Integer> menus = new HashMap<>();
    @BeforeEach
    void setUp() {
        menus.put("해산물파스타", 2);
        menus.put("레드와인", 1);
        menus.put("초코케이크", 1);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 에러")
    void toMapMenu_fail() {
        menus.put("짜장면", 1);

        assertThatCode(() -> Convert.toMapMenu(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MENU);
    }

    @Test
    @DisplayName("모두 존재하는 메뉴 성공")
    void toMapMenu_success() {
        assertThatCode(() -> Convert.toMapMenu(menus)).doesNotThrowAnyException();
    }
}