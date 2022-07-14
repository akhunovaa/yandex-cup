package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Collection;
import fizzbuzz.oop.Rule;
import fizzbuzz.oop.Value;

import java.util.List;

public class TagNumRulesCollection implements Collection {

    private final List<Rule> tags;

    public TagNumRulesCollection(List<Rule> tags) {
        this.tags = tags;
    }

    @Override
    public Value find(int number, Value defaultValue) {
        for (Rule rule : this.tags) {
            if (rule.isSuccess(number)) {
                return rule.getValue();
            }
        }
        return defaultValue;
    }
}
