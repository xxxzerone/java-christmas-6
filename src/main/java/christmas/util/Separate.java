package christmas.util;

import christmas.constant.Menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Separate {

    public static String[] split(String inputMenu, String delimiter) {
        return Arrays.stream(inputMenu.split(delimiter))
                .map(String::trim)
                .toArray(String[]::new);
    }

    public static Map<String, Integer> orderSplit(String[] splitMenu) {
        Map<String, Integer> orders = new HashMap<>();

        for (String order : splitMenu) {
            String[] menu = split(order, "-");

            orders.put(menu[0], Numbers.toInt(menu[1]));
        }
        return orders;
    }
}
