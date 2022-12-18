import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MovingAverage {


    public static void main(String[] args) {

        try {
            List<String> inputData = inputRead();
            String size = inputData.get(0);

            List<String> list = new ArrayList<>(Integer.parseInt(size));
            int limit = Integer.parseInt(inputData.get(2));

            String firstListData = inputData.get(1);

            for(String s: firstListData.split(" ")) {
                list.add(s);
            }

            List<String> movedAverageOverDatalist = moveAverageData(list, limit);

            if (inputData.isEmpty()) {
                throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
            }

            outputWrite(movedAverageOverDatalist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> moveAverageData(List<String> list, int limit) {
        List<String> result = new ArrayList<>(limit);
        int sum = calculateSumOfArray(list, 0, list.size());
        result.add(String.valueOf(sum / limit));
        for (int i = 0; i < limit; i++) {
            int currentAverage = 0;
            currentAverage = sum / limit;
            result.add(String.valueOf(currentAverage));
        }
        return result;
    }

    static int calculateSumOfArray(List<String> list, int i, int length) {
        if (i == length) {
            return 0;
        }
        return Integer.parseInt(list.get(i)) + calculateSumOfArray(list, i + 1, length);
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
