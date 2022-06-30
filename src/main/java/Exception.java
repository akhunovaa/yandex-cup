import java.util.Arrays;

public class Exception {

    public final static void main(String[] args) {
        Throwable exception = new Throwable();
        String testString = "dd rr";
        try {
            throw exception;
        } catch (Throwable e) {
            System.err.println("ERROR");
            System.err.println(Arrays.toString(testString.split("")));
        }
    }
}
