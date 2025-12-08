import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {
        Problem inputData = processInput();
        int answer = p1(inputData);
        System.out.println(answer);
    }

    static Problem processInput() {
        File inputFile = new File("input");
        Problem inputData = new Problem();

        try (Scanner reader = new Scanner(inputFile)) {
            String line = reader.nextLine();
            do {
                String[] intervalData = line.split("-");
                Long intervalStart = Long.parseLong(intervalData[0]);
                Long intervalStop = Long.parseLong(intervalData[1]);
                inputData.addInterval(intervalStart, intervalStop);
                line = reader.nextLine();
            } while (!line.isEmpty());

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                Long ingredientId = Long.parseLong(line);
                inputData.addIngredient(ingredientId);
            }
        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        }

        return inputData;
    }

    static int p1(Problem inputData) {
        int sum = 0;

        for (Long ingredientId : inputData.ingredientList) {
            for (int i = 0; i < inputData.ingredientIntervalList.size(); i++) {
                Interval interval = inputData.ingredientIntervalList.get(i);
                if (interval.getStart() <= ingredientId && ingredientId <= interval.getStop()) {
                    sum += 1;
                    break;
                }
            }
        }

        return sum;
    }
    
}
