package christmas.domain;

import christmas.constant.Category;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class WeekDayDiscount extends DiscountPolicy {

    private static final int DEFAULT_DISCOUNT_PRICE = 2_023;

    public WeekDayDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public Money calculateDiscount(Restaurant restaurant) {
        if (!isSatisfiedBy(restaurant.getReservationDate())) {
            return Money.ZERO;
        }

        int dessertCount = Category.findByDessertCount(restaurant.getMenus());
        return new Money(dessertCount * DEFAULT_DISCOUNT_PRICE);
    }

    private boolean isSatisfiedBy(LocalDate reservationDate) {
        return Arrays.stream(WeekDay.values())
                .anyMatch(weekday -> weekday.days == reservationDate.getDayOfWeek());
    }

    @Override
    public String toString() {
        return String.format("평일 할인: -%,d원", amount.getAmount());
    }

    private enum WeekDay {
        SUNDAY(DayOfWeek.SUNDAY),
        MONDAY(DayOfWeek.MONDAY),
        TUESDAY(DayOfWeek.TUESDAY),
        WEDNESDAY(DayOfWeek.WEDNESDAY),
        THURSDAY(DayOfWeek.THURSDAY);

        private final DayOfWeek days;

        WeekDay(DayOfWeek days) {
            this.days = days;
        }
    }
}
