package ru;

public interface ReadCall {

    String makeCall(String number);

    default String makeRead(String number) {
        return "default " + makeCall(number) + " " + number;
    }

}
