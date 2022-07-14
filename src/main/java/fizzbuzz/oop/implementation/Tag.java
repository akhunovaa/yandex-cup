package fizzbuzz.oop.implementation;

import fizzbuzz.oop.Value;

public class Tag implements Value {

    private final String value;

    public Tag(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
