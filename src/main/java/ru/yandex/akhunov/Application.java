package ru.yandex.akhunov;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        Application application = new Application();
        try {
            List<String> inputData = application.inputRead();

            if (inputData.isEmpty() || inputData.size() < 2) {
                throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
            }

            String dataOne = application.inputParse(inputData.get(0));
            String dataTwo = application.inputParse(inputData.get(1));

            char compareResult = application.isBinaryStringsEqual(dataOne, dataTwo);

            application.outputWrite(compareResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char isBinaryStringsEqual(String first, String second) {
        long x = Long.parseLong(first);
        long y = Long.parseLong(second);
        return (x < y) ? '<' : ((x == y) ? '=' : '>');
    }

    public void outputWrite(char data) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
//        bw.write(data);
//        bw.close();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(data));
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

    public String inputParse(String input) {
        StringBuilder first = new StringBuilder();

        // (o = 63) + (n = 62) + (e = 53)            =  178
        // (z = 74) + (e = 53) + (r = 66) + (o = 63) =  256
        int i = input.length() - 1;
        int carry = 0;

        while (i >= 0) {
            int sum = carry;
            // вычитаем '0' дабы получить int значение символа
            sum += input.charAt(i--) - '0';
            if (sum == 178) {
                first.append(1);
                sum = 0;
            } else if (sum == 256) {
                first.append(0);
                sum = 0;
            }
            carry = sum;
        }
        return first.reverse().toString();
    }


}
