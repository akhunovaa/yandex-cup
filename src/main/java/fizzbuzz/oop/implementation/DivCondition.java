package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Condition;
import fizzbuzz.oop.Truthy;

public class DivCondition implements Condition, Truthy {

    private final int divider;

    public DivCondition(int divider) {
        this.divider = divider;
    }

    @Override
    public boolean isTruthy(int number) {
        return number % this.divider == 0;
    }
}
