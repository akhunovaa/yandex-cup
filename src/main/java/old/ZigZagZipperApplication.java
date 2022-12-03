package old;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ZigZagZipperApplication {

    public static void main(String[] args) {

        try {
            List<String> inputData = ZigZagZipperApplication.inputRead();
            List<String> listOne = new ArrayList<>();
            List<String> listTwo = new ArrayList<>();
            int limit = Integer.parseInt(inputData.get(0));
            String firstListData = inputData.get(1);
            String secondListData = inputData.get(2);
            for(String s: firstListData.split(" ")) {
                listOne.add(s);
            }
            for(String s: secondListData.split(" ")) {
                listTwo.add(s);
            }

            List<String> mergedList = ZigZagZipperApplication.mergeLists(listOne, listTwo, limit);

            if (inputData.isEmpty()) {
                throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
            }

            ZigZagZipperApplication.outputWrite(mergedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> mergeLists(List<String> listOne, List<String> listTwo, int limit) {
        List<String> mergedList = new ArrayList<>(limit * 2);
        for(int i = 0; i < limit; i++) {
            mergedList.add(listOne.get(i));
            mergedList.add(" ");
            mergedList.add(listTwo.get(i));
            mergedList.add(" ");
        }
        return mergedList;
    }


    static List<String> inputRead() throws IOException {
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

    static void outputWrite(List<String> dataList) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            for(String data : dataList) {
                bw.write(String.valueOf(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
