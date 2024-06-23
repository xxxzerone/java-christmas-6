package christmas.constant;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    EMPTY("없음", 0);

    private final String name;
    private final int price;

    public static Badge findByAmount(int amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.price <= amount)
                .findFirst()
                .orElse(EMPTY);
    }

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
}
