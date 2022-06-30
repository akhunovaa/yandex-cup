package old;

public class Test {
//
//    public static void main(String[] args) {
//        for (int i = 0; i <= 100; i++) {
//            if(i % 3 == 0) {
//                System.out.println("Fizz");
//            } else if (i % 5 == 0){
//                System.out.println("Buzz");
//            }
//            if (i % 15 == 0){
//                System.out.println("АFizzBuzz");
//            }
//        }
//
//
//    }

    public static void main(String[] args) {

        System.out.println("\nDivided by 3: ");
        for (int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                System.out.print(i + ", ");
                System.out.println("Fizz");
            }
        }

        System.out.println("\n\nDivided by 5: ");
        for (int i = 1; i < 100; i++) {
            if (i % 5 == 0) {
                System.out.print(i + ", ");
                System.out.println("Buzz");
            }
        }

        System.out.println("\n\nDivided by 15: ");
        for (int i = 1; i < 100; i++) {
            if (i % 15 == 0) {
                System.out.print(i + ", ");
                System.out.println("FizzBuzz");
            }
        }
        System.out.println("\n");
    }

}
