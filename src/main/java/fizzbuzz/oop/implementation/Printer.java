package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Value;

public class Printer {

    private final Value context;

    public Printer(Value context) {
        this.context = context;
    }

    public void print() {
        System.out.println(this.context.getValue());
    }
}
