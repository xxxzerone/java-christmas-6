package christmas.validate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ValidateOrderTest {

    @Test
    @DisplayName("주문 메뉴 입력 형식 확인")
    void format_success() {
        String[] split = "해산물파스타-2,레드와인-1,초코케이크-1".split(",");

        assertThatCode(() -> ValidateOrder.isFormat(split)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("주문 메뉴 입력 형식 (-) 실패")
    void format_fail() {
        String[] split = "해산물파스타-2,레드와인-1,초코케이크".split(",");

        assertThatThrownBy(() -> ValidateOrder.isFormat(split))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴 입력 형식 (,) 실패")
    void format_fail2() {
        String[] split = "해산물파스타-2,레드와인-1초코케이크-1".split(",");

        assertThatThrownBy(() -> ValidateOrder.isFormat(split))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴 입력 형식 (-) 실패2")
    void format_fail3() {
        String[] split = "해산물파스타-2,-1,초코케이크-1".split(",");

        assertThatThrownBy(() -> ValidateOrder.isFormat(split))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴 입력 형식 개수 0 실패3")
    void format_fail4() {
        String[] split = "해산물파스타-2,레드와인-1,초코케이크-0".split(",");

        assertThatThrownBy(() -> ValidateOrder.isFormat(split))
                .isInstanceOf(IllegalArgumentException.class);
    }
}