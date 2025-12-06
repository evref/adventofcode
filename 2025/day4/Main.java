import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final int INPUT_FILE_LENGTH = 137;
    static final int INPUT_FILE_WIDTH = 137;

    public static void main(String[] args) {
        Grid grid = processInput();
        int answer = p2(grid);
        System.out.println(answer);
    }

    static Grid processInput() {
        File inputFile = new File("input");
        int[][] inputData = new int[INPUT_FILE_LENGTH][INPUT_FILE_WIDTH];

        try (Scanner reader = new Scanner(inputFile)) {
            int lineIdx = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                inputData[lineIdx] = processInputLine(line);
                lineIdx++;
            }
        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        }

        Grid grid = new Grid(inputData);

        return grid;
    }

    static int[] processInputLine(String line) {
        int[] processedLine = new int[INPUT_FILE_WIDTH];

        for (int i = 0; i < INPUT_FILE_WIDTH; i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '.') processedLine[i] = 0;
            else if (currentChar == '@') processedLine[i] = 1;
            else throw new IllegalArgumentException("ERROR: Input file contains illegal characters");
        }

        return processedLine;
    }

    static int p1(Grid grid) {
        return p1(grid, false);
    }
    static int p1(Grid grid, boolean removePaper) {
        int sum = 0;
        ArrayList<Integer> coordsToRemove = new ArrayList<Integer>();

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.sumAdjacents(x, y) < 4 && grid.get(x, y) == 1) {
                    sum++;
                    coordsToRemove.add(x);
                    coordsToRemove.add(y);
                }
            }
        }

        if (removePaper) {
            for (int i = 0; i < coordsToRemove.size(); i+=2) {
                grid.set(coordsToRemove.get(i), coordsToRemove.get(i+1), 0);
            }
        }

        return sum;
    }

    static int p2(Grid grid) {
        int sum = 0;
        int iterAdd = 0;

        do {
            iterAdd = p1(grid, true);
            sum += iterAdd;
        } while (iterAdd != 0);

        return sum;
    }
}
