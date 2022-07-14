package fizzbuzz.flags;

public class FizzBuzz {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            boolean isFizz = isFizz(i);
            boolean isBuzz = isBuzz(i);
            boolean isFizzBuzz = isFizzBuzz(i);
            if (!isFizz) {
                if (isBuzz) {
                    System.out.println("Buzz");
                } else if (isFizzBuzz) {
                    System.out.println("FizzBuzz");
                } else {
                    System.out.println(i);
                }
            } else {
                System.out.println("Fizz");
            }
        }
    }

    private static boolean isFizz(int number) {
        return number % 3 == 0;
    }

    private static boolean isBuzz(int number) {
        return number % 5 == 0;
    }

    private static boolean isFizzBuzz(int number) {
        return number % 3 == 0 && number % 5 == 0;
    }
}
