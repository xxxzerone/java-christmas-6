package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.Promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Restaurant {

    private LocalDate reservationDate;
    private Map<Menu, Integer> menus;
    private List<DiscountPolicy> discountPolicy = new ArrayList<>();

    public Restaurant(int day, Map<Menu, Integer> menus) {
        reservationDate = LocalDate.of(Promotion.YEAR, Promotion.MONTH, day);
        this.menus = menus;

        discountPolicy.add(new ChristmasDDayDiscount(this));
        discountPolicy.add(new WeekDayDiscount(this));
        discountPolicy.add(new WeekEndDiscount(this));
        discountPolicy.add(new SpecialDiscount(this));
        discountPolicy.add(new PresentationDiscount(this));
    }

    public int calculateTotalPriceBeforeDiscount() {
        return menus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int calculateTotalDiscount() {
        return discountPolicy.stream()
                .mapToInt(discount -> discount.getAmount().getAmount())
                .sum();
    }

    public int calculateTotalPriceAfterDiscount() {
        return calculateTotalPriceBeforeDiscount() - (calculateTotalDiscount() - getPresentationPrice());
    }

    private int getPresentationPrice() {
        return discountPolicy.stream()
                .filter(discount -> discount.getClass() == PresentationDiscount.class)
                .mapToInt(discount -> discount.getAmount().getAmount())
                .sum();
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    public List<DiscountPolicy> getDiscountPolicy() {
        return discountPolicy;
    }
}
