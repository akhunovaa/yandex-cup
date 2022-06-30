package old;

import java.io.*;

public class CalculatorSoutApplication {


    public static void main(String[] args) {

        CalculatorSoutApplication application = new CalculatorSoutApplication();
        String data = application.promptString();

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
        }

        String dataOne = data.split(" ")[0];
        String dataTwo = data.split(" ")[1];
        long answer = Long.parseLong(dataOne) + Integer.parseInt(dataTwo);
        System.out.println(answer);
    }

    public String promptString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException ignored) {

        }
        return str;
    }
}
