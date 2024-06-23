package christmas.domain;

import christmas.constant.Menu;

public class PresentationDiscount extends DiscountPolicy {

    private static final int DEFAULT_PRESENTATION_PRICE = 120_000;
    private static final int PRESENTATION_PRICE = Menu.CHAMPAGNE.getPrice();

    public PresentationDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public Money calculateDiscount(Restaurant restaurant) {
        if (!isSatisfiedBy(restaurant.calculateTotalPriceBeforeDiscount())) {
            return Money.ZERO;
        }

        return new Money(PRESENTATION_PRICE);
    }

    private boolean isSatisfiedBy(int price) {
        return DEFAULT_PRESENTATION_PRICE <= price;
    }

    @Override
    public String toString() {
        return String.format("증정 이벤트: -%,d원", amount.getAmount());
    }
}
