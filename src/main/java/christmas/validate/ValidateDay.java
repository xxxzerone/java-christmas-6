package christmas.validate;

import christmas.constant.Promotion;

import java.util.regex.Pattern;

public class ValidateDay {

    private static final Pattern NUMBER_DIGIT = Pattern.compile("^[0-9]{1,2}$");

    public static boolean isNumber(String inputDay) {
        return NUMBER_DIGIT.matcher(inputDay).find();
    }

    public static boolean isRange(int day) {
        return Promotion.START_DAY <= day && day <= Promotion.END_DAY;
    }
}
