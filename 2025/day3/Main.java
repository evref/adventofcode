import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static final int INPUT_FILE_LENGTH = 200;

    public static void main(String[] args) {
        String[] inputData = processInput();
        Long answer = p2(inputData);
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
            // Check every element but the last, needs to be a two digit number
            int maxValueIdx = findLargestNumInStringIdx(inputData[i], 0, inputData[i].length()-1);

            int secondMaxValueIdx = findLargestNumInStringIdx(inputData[i], maxValueIdx+1, inputData[i].length());

            String number = "" + inputData[i].charAt(maxValueIdx) + inputData[i].charAt(secondMaxValueIdx);

            sum += Integer.parseInt(number);
        }

        return sum;
    }

    static Long p2(String[] inputData) {
        Long sum = 0L;

        for (int i = 0; i < inputData.length; i++) {
            int[] maxValueIndicies = new int[12];
            int currentIdx = 0;

            for (int j = 0; j < maxValueIndicies.length; j++) {
                int cap = inputData[i].length() + j - 11;
                maxValueIndicies[j] = findLargestNumInStringIdx(inputData[i], currentIdx, cap);
                currentIdx = maxValueIndicies[j] + 1;
            }

            String number = "";
            for (int j = 0; j < maxValueIndicies.length; j++) {
                number += inputData[i].charAt(maxValueIndicies[j]);
            }

            sum += Long.parseLong(number);
        }

        return sum;
    }

    static int findLargestNumInStringIdx(String string, int startIdx, int endIdx) {
        int maxValue = 0;
        int maxValueIdx = 0;

        for (int j = startIdx; j < endIdx; j++) {
            int currentValue = string.charAt(j);

            if (maxValue < currentValue) {
                maxValue = currentValue;
                maxValueIdx = j;
            }
        }

        return maxValueIdx;
    }
}
