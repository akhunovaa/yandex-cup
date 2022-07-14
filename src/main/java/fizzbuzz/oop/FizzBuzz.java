package fizzbuzz.oop;

import fizzbuzz.oop.implementation.*;

import java.util.List;

public class FizzBuzz {

    private final static int MAX_NUM = 100;

    public static void main(String[] args) {
        Rule fizzBuzzRule = new TagNumRule(new Tag("FizzBuzz"), new AndStrategy(List.of(new DivCondition(3), new DivCondition(5))));
        Rule fizzRule = new TagNumRule(new Tag("Fizz"), new AndStrategy(List.of(new DivCondition(3))));
        Rule buzzRule = new TagNumRule(new Tag("Buzz"), new AndStrategy(List.of(new DivCondition(5))));

        Collection tagsCollection = new TagNumRulesCollection(List.of(fizzBuzzRule, fizzRule, buzzRule));

        for (int i = 1; i < MAX_NUM; i++) {
            new Printer(tagsCollection.find(i, new Tag(String.valueOf(i)))).print();
        }

    }

}
