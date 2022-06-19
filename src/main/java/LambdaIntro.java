import ru.ReadCall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaIntro {

    public static void main(String[] args) {

        ISum summer = new ISum() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };

        System.out.println(summer.sum(2, 2));

        // f(x1, x2) = a * x1 + b * x2
        ISum summator = (a, b) -> a + b;
        // 1. ISum summer2 => ISum (!)
        // 2. ISum.sum(int a, int b) => (int a, int b)
        // 3. int ISum.sum => (int)(a + b)
        System.out.println(summator.sum(1, 1));

        ISum summatorSecond = Integer::sum;

        System.out.println(summatorSecond.sum(4, 4));

        final List<String> data = new ArrayList<>() {{
            add("one");
            add("two");
            add("three");
            add("four");
            add("five");
            add("six");
            add("seven");
            add("eight");
            add("nine");
            add("ten");
        }};

        System.out.println(data);

//        Reader read = new Reader();
//        System.out.println(read.getText());

        Function<Reader, String> readerStringFunction = Reader::getText; // (Reader) -> String
        String text = readerStringFunction.apply(new Reader());
        System.out.println(text);


        ReadCall readCall = new ReadCall() {

            @Override
            public String makeCall(String number) {
                return "Calling to " + " " + number;
            }

            @Override
            public String makeRead(String number) {
                return "nothing";
            }
        };
        System.out.println(readCall.makeCall("+792654"));
        System.out.println(readCall.makeRead("+792654"));

        String[] arrayOfWords = {"STACK", "OOOVVVER"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        List<String> drt = streamOfWords.map(element -> element.split("")) //Converting word in to array of letters
                .flatMap(Arrays::stream).distinct() //flattens each generated stream in to a single stream
                .collect(Collectors.toList());
        System.out.println(drt);
    }

    @FunctionalInterface
    public interface ISum {
        int sum(int a, int b);
    }

    static class Reader {
        final String text = "reader";

        public String getText(/*Reader this*/) {
            return text;
        }
    }
}
