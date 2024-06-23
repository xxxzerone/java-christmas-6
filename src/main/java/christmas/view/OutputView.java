package christmas.view;

import christmas.constant.Badge;
import christmas.constant.Promotion;
import christmas.domain.DiscountPolicy;
import christmas.domain.Money;
import christmas.domain.PresentationDiscount;
import christmas.domain.Restaurant;

import java.util.List;

public class OutputView {

    private static final String WON_FORMATTER = "%,d원";

    public void previewEventBenefits(Restaurant restaurant) {
        previewOrderMenu(restaurant);
        previewTotalAmountBeforeDiscount(restaurant);
        previewBenefits(restaurant);
    }

    private void previewOrderMenu(Restaurant restaurant) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n",
                restaurant.getReservationDate().getDayOfMonth());
        System.out.println("<주문 메뉴>");
        System.out.println(restaurant);
    }

    private void previewTotalAmountBeforeDiscount(Restaurant restaurant) {
        int totalAmount = restaurant.calculateTotalPriceBeforeDiscount();

        System.out.println("<할인 전 총주문 금액>");

        if (totalAmount < Promotion.DEFAULT_ORDER_AMOUNT) {
            System.out.printf("총주문 금액 %s 이상부터 이벤트가 적용됩니다%n",
                    String.format(WON_FORMATTER, Promotion.DEFAULT_ORDER_AMOUNT));
        }

        System.out.println(String.format(WON_FORMATTER, totalAmount));
        System.out.println();
    }

    private void previewBenefits(Restaurant restaurant) {
        List<DiscountPolicy> discountPolicies = restaurant.getDiscountPolicy();
        int totalAmountBenefit = restaurant.calculateTotalDiscount();
        int paymentAmount = restaurant.calculateTotalPriceAfterDiscount();

        previewPresentation(discountPolicies);
        previewBenefitsDetail(discountPolicies, totalAmountBenefit);
        previewTotalAmountBenefit(totalAmountBenefit);
        previewPaymentAmount(paymentAmount);
        previewBadge(totalAmountBenefit);
    }

    private void previewBadge(int totalAmountBenefit) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(Badge.findByAmount(totalAmountBenefit).getName());
    }

    private void previewPresentation(List<DiscountPolicy> discountPolicies) {
        Money amount = Money.ZERO;
        for (DiscountPolicy discount : discountPolicies) {
            if (discount.getClass() == PresentationDiscount.class) {
                amount = discount.getAmount();
            }
        }

        System.out.println("<증정 메뉴>");
        if (amount == Money.ZERO) {
            System.out.println("없음");
        } else {
            System.out.println("샴페인 1개");
        }
        System.out.println();
    }

    private void previewBenefitsDetail(List<DiscountPolicy> discountPolicies, int totalAmountBenefit) {
        System.out.println("<혜택 내역>");

        if (totalAmountBenefit < Promotion.DEFAULT_ORDER_AMOUNT) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        for (DiscountPolicy discount : discountPolicies) {
            if (discount.getAmount() != Money.ZERO) {
                System.out.printf("%s%n", discount);
            }
        }
        System.out.println();
    }

    private void previewTotalAmountBenefit(int totalAmountBenefit) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format(WON_FORMATTER, -totalAmountBenefit));
        System.out.println();
    }

    private void previewPaymentAmount(int payAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(WON_FORMATTER, payAmount));
        System.out.println();
    }

}
