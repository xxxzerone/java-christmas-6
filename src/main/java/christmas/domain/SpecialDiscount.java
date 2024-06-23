package christmas.domain;

import christmas.constant.Promotion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialDiscount extends DiscountPolicy {

    private static final int DEFAULT_DISCOUNT_PRICE = 1_000;
    private static final List<LocalDate> SPECIALDAY;

    static {
        List<LocalDate> specialDay = new ArrayList<>();
        LocalDate startDate = LocalDate.of(Promotion.YEAR, Promotion.MONTH, Promotion.START_DAY);
        LocalDate endDate = LocalDate.of(Promotion.YEAR, Promotion.MONTH, Promotion.END_DAY);
        LocalDate christmas = LocalDate.of(Promotion.YEAR, Promotion.MONTH, 25);

        while (!startDate.isAfter(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY
                    || startDate.isEqual(christmas)) {
                specialDay.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }

        SPECIALDAY = Collections.unmodifiableList(specialDay);
    }

    public SpecialDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public Money calculateDiscount(Restaurant restaurant) {
        if (!isSatisfiedBy(restaurant.getReservationDate())) {
            return Money.ZERO;
        }

        return new Money(DEFAULT_DISCOUNT_PRICE);
    }

    private boolean isSatisfiedBy(LocalDate reservationDate) {
        return SPECIALDAY.stream()
                .anyMatch(special -> special.isEqual(reservationDate));
    }

    @Override
    public String toString() {
        return String.format("특별 할인: -%,d원", amount.getAmount());
    }
}
