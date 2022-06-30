package old;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorApplication {

    public static void main(String[] args) {

        CalculatorApplication application = new CalculatorApplication();
        try {
            List<String> inputData = application.inputRead();

            if (inputData.isEmpty()) {
                throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
            }

            String dataOne = inputData.get(0).split(" ")[0];
            String dataTwo = inputData.get(0).split(" ")[1];
            int answer = Integer.parseInt(dataOne) + Integer.parseInt(dataTwo);
            application.outputWrite(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> inputRead() throws IOException {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void outputWrite(int data) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
//        bw.write(String.valueOf(data));
//        bw.close();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(data));
//            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
