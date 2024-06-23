package christmas.controller;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.domain.Restaurant;
import christmas.util.Convert;
import christmas.util.Numbers;
import christmas.util.Separate;
import christmas.validate.ValidateDay;
import christmas.validate.ValidateOrder;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int reservationDay = reservationDay();
        Map<Menu, Integer> menuOrder = reservationMenu();

        Restaurant restaurant = new Restaurant(reservationDay, menuOrder);

        outputView.previewEventBenefits(restaurant);
    }

    private int reservationDay() {
        while (true) {
            String inputDay = inputView.readDay();
            try {
                validateNumber(inputDay);
                int day = Numbers.toInt(inputDay);

                validateRange(day);
                return day;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<Menu, Integer> reservationMenu() {
        while (true) {
            String inputMenu = inputView.readMenu();
            String[] splitMenu = Separate.split(inputMenu, ",");
            try {
                ValidateOrder.isFormat(splitMenu);

                Map<Menu, Integer> menuOrder = Convert.toMapMenu(Separate.orderSplit(splitMenu));
                ValidateOrder.menuTotalCount(menuOrder);
                ValidateOrder.menuDuplicate(menuOrder, splitMenu);
                ValidateOrder.onlyBeverage(menuOrder);

                return menuOrder;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateNumber(String inputDay) {
        if (!ValidateDay.isNumber(inputDay)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY);
        }
    }

    private void validateRange(int day) {
        if (!ValidateDay.isRange(day)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY);
        }
    }
}
