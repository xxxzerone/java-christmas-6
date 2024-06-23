package christmas.domain;

public abstract class DiscountPolicy {

    protected Money amount;

    protected abstract Money calculateDiscount(Restaurant restaurant);

    public Money getAmount() {
        return amount;
    }
}
