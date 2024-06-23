package christmas;

import christmas.controller.PromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PromotionController promotionController = new PromotionController(new InputView(), new OutputView());
        promotionController.start();
    }
}
