package christmas.domain;

public class Money {

    public static final Money ZERO = Money.wons(0);

    private int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    private static Money wons(int amount) {
        return new Money(amount);
    }

    public int getAmount() {
        return amount;
    }
}
