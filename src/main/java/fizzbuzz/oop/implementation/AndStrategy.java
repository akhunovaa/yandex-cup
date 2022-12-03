package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Condition;
import fizzbuzz.oop.Strategy;
import fizzbuzz.oop.Truthy;

import java.util.List;

public class AndStrategy implements Strategy, Truthy {

    private final List<Condition> conditionsOrStrategies;

    public AndStrategy(List<Condition> conditionsOrStrategies) {
        this.conditionsOrStrategies = conditionsOrStrategies;
    }

    @Override
    public boolean isTruthy(int number) {
        for (Condition conditionsOrStrategy : conditionsOrStrategies) {
            if (!conditionsOrStrategy.isTruthy(number)) {
                return false;
            }
        }
        return true;
    }

}
