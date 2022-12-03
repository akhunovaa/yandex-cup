package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Rule;
import fizzbuzz.oop.Strategy;
import fizzbuzz.oop.Value;

public class TagNumRule implements Rule {

    private final Value tag;
    private final Strategy strategy;

    public TagNumRule(Value tag, Strategy strategy) {
        this.tag = tag;
        this.strategy = strategy;
    }

    @Override
    public boolean isSuccess(int number) {
        return this.strategy.isTruthy(number);
    }

    @Override
    public Value getValue() {
        return this.tag;
    }
}
