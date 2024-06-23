package christmas.domain;

import christmas.constant.Promotion;

import java.time.LocalDate;
import java.time.Period;

public class ChristmasDDayDiscount extends DiscountPolicy {

    private static final int DEFAULT_DISCOUNT_PRICE = 1_000;
    private static final int INCREMENT_PRICE = 100;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    private static final LocalDate FROM = LocalDate.of(Promotion.YEAR, Promotion.MONTH, START_DAY);
    private static final LocalDate TO = LocalDate.of(Promotion.YEAR, Promotion.MONTH, END_DAY);

    public ChristmasDDayDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public Money calculateDiscount(Restaurant restaurant) {
        LocalDate reservationDate = restaurant.getReservationDate();
        if (!isSatisfiedBy(reservationDate)) {
            return Money.ZERO;
        }

        Period periodDate = Period.between(FROM, reservationDate);
        return new Money(periodDate.getDays() * INCREMENT_PRICE + DEFAULT_DISCOUNT_PRICE);
    }

    public boolean isSatisfiedBy(LocalDate reservationDate) {
        return reservationDate.compareTo(FROM) >= 0 && reservationDate.compareTo(TO) <= 0;
    }

    @Override
    public String toString() {
        return String.format("크리스마스 디데이 할인: -%,d원", amount.getAmount());
    }
}
