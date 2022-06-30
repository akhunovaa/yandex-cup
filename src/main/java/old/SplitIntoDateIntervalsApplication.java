package old;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

/**
 * Всего нужно поддержать пять видов временных интервалов:
 * WEEK — неделя с понедельника по воскресенье.
 * MONTH — месяц.
 * QUARTER — интервалы в три месяца: январь — март, апрель — июнь, июль — сентябрь, октябрь — декабрь.
 * YEAR — год c 1 января по 31 декабря.
 * REVIEW — периоды, за которые оцениваются достижения сотрудников Яндекса. Летний период длится с 1 апреля по 30 сентября,
 * зимний — с 1 октября по 31 марта.
 */
public class SplitIntoDateIntervalsApplication {

    public static void main(String[] args) {
        SplitIntoDateIntervalsApplication application = new SplitIntoDateIntervalsApplication();
        List<String> inputData = null;
        try {
            inputData = application.inputRead();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputData == null || inputData.isEmpty() || inputData.size() < 2) {
            throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
        }

        String timeInterval = inputData.get(0).trim();
        String dateFrom = inputData.get(1).split(" ")[0];
        String dateTo = inputData.get(1).split(" ")[1];

        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);

            LocalDate start = convertToLocalDateViaInstant(startDate);
            LocalDate stop = convertToLocalDateViaInstant(endDate);

            String data;
            switch (timeInterval) {
                case "WEEK":
                    data = splitDatesWeeks(start, stop);
                    break;
                case "MONTH":
                    data = splitDatesMonth(start, stop);
                    break;
                case "QUARTER":
                    data = splitDatesQuarter(start, stop);
                    break;
                case "YEAR":
                    data = splitDatesYear(start, stop);
                    break;
                default:
                    data = splitDatesReview(start, stop);
            }


            application.outputWrite(data);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String splitDatesWeeks(LocalDate start, LocalDate stop) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 1;
        LocalDate date = start;
        stringBuilder.append("\n");
        while (date.isBefore(stop)) {
            idx++;
            stringBuilder.append(date);
            stringBuilder.append(" ");
            LocalDate lastDayOfWeek = date.with(ChronoField.DAY_OF_WEEK, 7);
            if (!lastDayOfWeek.isAfter(stop)) {
                stringBuilder.append(lastDayOfWeek);
            }
            date = date.plusWeeks(1).with(ChronoField.DAY_OF_WEEK, 1);
            if (date.isEqual(stop) || date.isAfter(stop)) {
                stringBuilder.append("\n");
                stringBuilder.append(stop);
                stringBuilder.append(" ");
                stringBuilder.append(stop);
            }
            stringBuilder.append("\n");
        }
        return idx + stringBuilder.toString();
    }

    public static String splitDatesQuarter(LocalDate start, LocalDate stop) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 1;
        LocalDate date = start;
        stringBuilder.append("\n");
        while (date.isBefore(stop)) {
            idx++;
            stringBuilder.append(date);
            stringBuilder.append(" ");
            LocalDate lastDateOfQuarter = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
            if (!lastDateOfQuarter.isAfter(stop)) {
                stringBuilder.append(lastDateOfQuarter);
            }
            date = date.plusMonths(3).withDayOfMonth(1);
            if (date.isEqual(stop) || date.isAfter(stop)) {
                stringBuilder.append("\n");
                stringBuilder.append(stop);
                stringBuilder.append(" ");
                stringBuilder.append(stop);
            }
            stringBuilder.append("\n");
        }
        return idx + stringBuilder.toString();
    }

    public static String splitDatesYear(LocalDate start, LocalDate stop) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 1;
        LocalDate date = start;
        stringBuilder.append("\n");
        while (date.isBefore(stop)) {
            idx++;
            stringBuilder.append(date);
            stringBuilder.append(" ");
            LocalDate firstDay = date.with(firstDayOfYear());
            LocalDate lastDayOfYear = date.with(lastDayOfYear());
            if (!lastDayOfYear.isAfter(stop)) {
                stringBuilder.append(lastDayOfYear);
            }
            date = firstDay.plusYears(1);
            if (date.isEqual(stop) || date.isAfter(stop)) {
                stringBuilder.append("\n");
                stringBuilder.append(stop);
                stringBuilder.append(" ");
                stringBuilder.append(stop);
            }
            stringBuilder.append("\n");
        }
        return idx + stringBuilder.toString();
    }

    public static String splitDatesReview(LocalDate start, LocalDate stop) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 1;
        LocalDate date = start;
        stringBuilder.append("\n");
        while (date.isBefore(stop)) {
            idx++;
            stringBuilder.append(date);
            stringBuilder.append(" ");
            LocalDate lastDateOfReview = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
            if (!lastDateOfReview.isAfter(stop)) {
                stringBuilder.append(lastDateOfReview);
            }
            date = date.plusMonths(6);
            if (date.isEqual(stop) || date.isAfter(stop)) {
                stringBuilder.append("\n");
                stringBuilder.append(stop);
                stringBuilder.append(" ");
                stringBuilder.append(stop);
            }
            stringBuilder.append("\n");
        }
        return idx + stringBuilder.toString();
    }

    public static String splitDatesMonth(LocalDate start, LocalDate stop) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 0;
        LocalDate date = start;
        stringBuilder.append("\n");
        while (date.isBefore(stop)) {
            idx++;
            stringBuilder.append(date);
            stringBuilder.append(" ");
            LocalDate lastDateOfMonth = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
            if (!lastDateOfMonth.isAfter(stop)) {
                stringBuilder.append(lastDateOfMonth);
            }
            date = date.plusMonths(1).withDayOfMonth(1);
            if (date.isEqual(stop) || date.isAfter(stop)) {
                stringBuilder.append(" ");
                stringBuilder.append(stop);
                break;
            }
            stringBuilder.append("\n");
        }
        return idx + stringBuilder.toString();
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

    public void outputWrite(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
