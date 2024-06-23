package christmas.util;

import christmas.constant.Menu;

import java.util.EnumMap;
import java.util.Map;

public class Convert {

    public static Map<Menu, Integer> toMapMenu(Map<String, Integer> orders) throws IllegalArgumentException {
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        for (Map.Entry<String, Integer> order : orders.entrySet()) {
            Menu menu = Menu.findByMenu(order.getKey());
            menus.put(menu, order.getValue());
        }

        return menus;
    }
}
