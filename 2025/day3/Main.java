import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static final int INPUT_FILE_LENGTH = 200;


    public static void main(String[] args) {
        String[] inputData = processInput();
        int answer = p1(inputData);
        System.out.println(answer);
    }

    static String[] processInput() {
        File inputFile = new File("input");
        String[] inputData = new String[INPUT_FILE_LENGTH];

        try (Scanner reader = new Scanner(inputFile)) {
            for (int i = 0; i < inputData.length; i++) {
                inputData[i] = reader.nextLine();
            }
        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        }

        return inputData;
    }

    static int p1(String[] inputData) {
        int sum = 0;

        for (int i = 0; i < inputData.length; i++) {
            int maxValueIdx = 0;
            int maxValue = 0;

            // Check every element but the last, needs to be a two digit number
            for (int j = 0; j < inputData[i].length()-1; j++) {
                int currentValue = inputData[i].charAt(j);

                if (maxValue < currentValue) {
                    maxValue = currentValue;
                    maxValueIdx = j;
                }
            }

            int secondMaxValueIdx = 0;
            int secondMaxValue = 0;

            for (int j = maxValueIdx+1; j < inputData[i].length(); j++) {
                int currentValue = inputData[i].charAt(j);

                if (secondMaxValue < currentValue) {
                    secondMaxValue = currentValue;
                    secondMaxValueIdx = j;
                }
            }

            String number = "" + inputData[i].charAt(maxValueIdx) + inputData[i].charAt(secondMaxValueIdx);

            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
