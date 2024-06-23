package christmas.validate;

import christmas.constant.Category;
import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.constant.Promotion;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateOrder {

    private static final Pattern MENU_FORMAT = Pattern.compile("([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+)-([1-9]+)");

    public static void isFormat(String[] inputMenu) {
        Arrays.stream(inputMenu)
                .filter(menu -> isNotOrderFormat(menu))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_MENU);
                });
    }

    public static void menuTotalCount(Map<Menu, Integer> orders) {
        if (Promotion.TOTAL_MENU_COUNT < sumMenuCount(orders.values())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU);
        }
    }

    private static int sumMenuCount(Collection<Integer> values) {
        return values.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void menuDuplicate(Map<Menu, Integer> orders, String[] menuGroup) {
        if (orders.size() != menuGroup.length) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU);
        }
    }
    public static void onlyBeverage(Map<Menu, Integer> orders) {
        if (Category.isOnlyDrink(orders.keySet())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU);
        }
    }

    private static boolean isNotOrderFormat(String menu) {
        return !MENU_FORMAT.matcher(menu).matches();
    }
}
