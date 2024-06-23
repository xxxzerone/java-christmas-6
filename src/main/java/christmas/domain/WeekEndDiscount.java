package christmas.domain;

import christmas.constant.Category;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class WeekEndDiscount extends DiscountPolicy {

    private static final int DEFAULT_DISCOUNT_PRICE = 2_023;

    public WeekEndDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public Money calculateDiscount(Restaurant restaurant) {
        if (!isSatisfiedBy(restaurant.getReservationDate())) {
            return Money.ZERO;
        }

        int mainCount = Category.findByMainCount(restaurant.getMenus());
        return new Money(mainCount * DEFAULT_DISCOUNT_PRICE);
    }

    private boolean isSatisfiedBy(LocalDate reservationDate) {
        return Arrays.stream(WeekEnd.values())
                .anyMatch(weekday -> weekday.days == reservationDate.getDayOfWeek());
    }

    @Override
    public String toString() {
        return String.format("주말 할인: -%,d원", amount.getAmount());
    }

    private enum WeekEnd {
        FRIDAY(DayOfWeek.FRIDAY),
        SATURDAY(DayOfWeek.SATURDAY);

        private final DayOfWeek days;

        WeekEnd(DayOfWeek days) {
            this.days = days;
        }
    }
}
