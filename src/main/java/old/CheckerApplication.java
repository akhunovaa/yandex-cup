package old;

import java.io.*;
import java.util.*;

/**
 * 8 8    # размеры доски
 * 3      # количество белых шашек на поле
 * 1 1    # поля, на которых стоят белые шашки
 * 2 6
 * 6 6
 * 3      # количество черных шашек на поле
 * 2 2    # поля с черными шашками
 * 7 7
 * 8 8
 * white  # чей ход: "white", если белых, и "black" — если черных
 */
public class CheckerApplication {

    private static final String WHITE_CHECKER = "white";
    private static final String BLACK_CHECKER = "black";

    public static void main(String[] args) {

        CheckerApplication checkerApplication = new CheckerApplication();
        try {
            List<String> inputData = checkerApplication.inputRead();

            if (inputData.isEmpty() || inputData.size() < 6) {
                throw new IllegalArgumentException("Отсутствуют входные данные в файле input.txt!");
            }

            Field checkersField = checkerApplication.checkerFieldBuild(inputData);
            boolean playResult = checkersField.play();
            checkerApplication.outputWrite(playResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int integerParameterParse(String data) {
        return Integer.parseInt(data.trim());
    }

    /**
     * Создает игровое поле с шашками
     *
     * @param inputData входные данные необходимые для создания игрового поля
     * @return возвращает сформированное игровое поле с шашками на ней
     */
    public Field checkerFieldBuild(List<String> inputData) {
        Field gameField;

        // xLength - длина поля по X оси
        // yLength - длина поля по Y оси
        int xLength = integerParameterParse(inputData.get(0).split(" ")[0]);
        int yLength = integerParameterParse(inputData.get(0).split(" ")[1]);

        // создаем игровое поле
        gameField = new Field(xLength, yLength);

        // кол-во игровых шашек белого цвета
        int whiteCheckersCount = integerParameterParse(inputData.get(1));

        // сдвиг для шашек белого цвета
        int whiteCheckerOffset = 2;

        // сдвиг для шашек черного цвета
        int blackCheckerOffset = whiteCheckersCount + whiteCheckerOffset;

        // инициализируем игровые шашки белого цвета для добавления в игровое поле
        for (int i = whiteCheckerOffset; i < whiteCheckersCount + whiteCheckerOffset; i++) {
            int xCoordinate = integerParameterParse(inputData.get(i).split(" ")[0]);
            int yCoordinate = integerParameterParse(inputData.get(i).split(" ")[1]);
            Checker checker = new Checker(WHITE_CHECKER, xCoordinate, yCoordinate);
            gameField.checkerAdd(checker);
        }

        // кол-во игровых шашек черного цвета
        int blackCheckersCount = integerParameterParse(inputData.get(blackCheckerOffset));

        // инициализируем игровые шашки черного цвета для добавления в игровое поле
        for (int i = blackCheckerOffset + 1; i <= blackCheckersCount + blackCheckerOffset; i++) {
            int xCoordinate = integerParameterParse(inputData.get(i).split(" ")[0]);
            int yCoordinate = integerParameterParse(inputData.get(i).split(" ")[1]);
            Checker checker = new Checker(BLACK_CHECKER, xCoordinate, yCoordinate);
            gameField.checkerAdd(checker);
        }

        // определяем очередность ходить шашек белого цвета
        gameField.isWhiteCheckersWalkTurn = inputData.get(inputData.size() - 1).equals(WHITE_CHECKER);

        return gameField;
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

    public void outputWrite(boolean data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Field {
        int xLength;
        int yLength;
        Set<Checker> checkers;
        boolean isWhiteCheckersWalkTurn;

        Field(int xLength, int yLength) {
            this.xLength = xLength;
            this.yLength = yLength;
            this.checkers = new HashSet<>();
            this.isWhiteCheckersWalkTurn = true;
        }

        void checkerAdd(Checker checker) {
            this.checkers.add(checker);
        }

        Checker findChecker(int xCoordinate, int yCoordinate) {
            Checker returnChecker = null;
            for (Checker checker : checkers) {
                if (checker.xCoordinate == xCoordinate && checker.yCoordinate == yCoordinate) {
                    returnChecker = checker;
                }
            }
            return returnChecker;
        }

        Checker searchOpponentChecker(int xCoordinate, int yCoordinate) {
            Checker opponentChecker;

            int diagUpXRight = xCoordinate + 1;
            int diagUpYRight = yCoordinate + 1;

            opponentChecker = findChecker(diagUpXRight, diagUpYRight);

            if (opponentChecker != null) {
                return opponentChecker;
            }

            int diagUpXLeft = xCoordinate - 1;
            int diagUpYLeft = yCoordinate + 1;

            opponentChecker = findChecker(diagUpXLeft, diagUpYLeft);

            if (opponentChecker != null) {
                return opponentChecker;
            }

            int diagDownXRight = xCoordinate + 1;
            int diagDownYRight = yCoordinate - 1;

            opponentChecker = findChecker(diagDownXRight, diagDownYRight);

            return opponentChecker;
        }

        boolean play() {

            boolean isOpponentCheckerWasSlain = false;

            // проверяем наличие шашек на игровом поле
            if (checkers == null || checkers.isEmpty()) {
                throw new IllegalArgumentException("Для игры на данном поле нет игровых шашек. Необходимо их добавить!");
            }

            for (Checker checker : checkers) {
                Checker opponent;
                int xCoordinate = checker.xCoordinate;
                int yCoordinate = checker.yCoordinate;

                if (isWhiteCheckersWalkTurn && checker.checkerColour.equals(WHITE_CHECKER)) {
                    opponent = searchOpponentChecker(xCoordinate, yCoordinate);
                    if (opponent != null && opponent.checkerColour.equals(BLACK_CHECKER)) {
                        isOpponentCheckerWasSlain = true;
                    }
                } else if (!isWhiteCheckersWalkTurn && checker.checkerColour.equals(BLACK_CHECKER)) {
                    opponent = searchOpponentChecker(xCoordinate, yCoordinate);
                    if (opponent != null && opponent.checkerColour.equals(WHITE_CHECKER)) {
                        isOpponentCheckerWasSlain = true;
                    }
                }
            }

            return isOpponentCheckerWasSlain;
        }
    }

    static class Checker {
        int xCoordinate;
        int yCoordinate;
        String checkerColour;

        public Checker(String checkerColour, int xCoordinate, int yCoordinate) {
            this.checkerColour = checkerColour;
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Checker checker = (Checker) o;

            if (xCoordinate != checker.xCoordinate) return false;
            if (yCoordinate != checker.yCoordinate) return false;
            return checkerColour.equals(checker.checkerColour);
        }

        @Override
        public int hashCode() {
            int result = xCoordinate;
            result = 31 * result + yCoordinate;
            result = 31 * result + checkerColour.hashCode();
            return result;
        }
    }
}
