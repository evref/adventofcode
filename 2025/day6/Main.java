import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {
        Problem problem = processInput();
        Long answer = p1(problem);
        System.out.println(answer);
    }

    static Problem processInput() {
        File inputFile = new File("input");
        Problem problemData = new Problem();

        try (Scanner reader = new Scanner(inputFile)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] splitLine = line.trim().split("\\s+");

                if (splitLine[0].equals("+") || splitLine[0].equals("*")) {
                    problemData.setOperators(splitLine);
                } else {
                    int[] lineNumbers = new int[splitLine.length];
                    for (int i = 0; i < splitLine.length; i++) {
                        lineNumbers[i] = Integer.parseInt(splitLine[i]);
                    }
                    problemData.appendData(lineNumbers);
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }

        return problemData;
    }
    
    static Long p1(Problem problem) {
        ArrayList<int[]> data = problem.getData();
        String[] operators = problem.getOperators();
        Long sum = 0L;

        for (int i = 0; i < operators.length; i++) {
            boolean isMultiplication = operators[i].equals("*");
            Long currentTotal = isMultiplication ? 1L : 0L;

            for (int j = 0; j < data.size(); j++) {
                if (isMultiplication) currentTotal *= data.get(j)[i];
                else currentTotal += data.get(j)[i];
            }
            
            sum += currentTotal;
        }
        
        return sum;
    }
}
