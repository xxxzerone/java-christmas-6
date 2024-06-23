package christmas.constant;

import java.util.List;
import java.util.Map;
import java.util.Set;

public enum Category {

    APPETIZER("애피타이저", List.of(Menu.MUSHROOM_CREAM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", List.of(Menu.T_BONE_STEAK, Menu.PORK_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", List.of(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    DRINK("음료", List.of(Menu.COKE_ZERO, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final String name;
    private final List<Menu> menus;

    Category(String name, List<Menu> menus) {
        this.name = name;
        this.menus = menus;
    }

    public static int findByDessertCount(Map<Menu, Integer> menus) {
        return menus.entrySet().stream()
                .filter(entry -> findByDessert(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private static boolean findByDessert(Menu menu) {
        return DESSERT.menus.stream()
                .anyMatch(dessert -> dessert == menu);
    }

    public static int findByMainCount(Map<Menu, Integer> menus) {
        return menus.entrySet().stream()
                .filter(entry -> findByMain(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private static boolean findByMain(Menu menu) {
        return MAIN.menus.stream()
                .anyMatch(main -> main == menu);
    }

    public static boolean isOnlyDrink(Set<Menu> menus) {
        List<Menu> drinks = DRINK.menus;
        return menus.stream()
                .noneMatch(menu -> !drinks.contains(menu));
    }

    public String getName() {
        return name;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
