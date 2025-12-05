import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] tempInput = processInput();
        int tempAnswer = p1(tempInput);
        System.out.println(tempAnswer);
    }

    static String[] processInput() {
        File tempFile = new File("input");
        String myInput = "";

        try(Scanner tempScanner = new Scanner(tempFile)) {
            myInput = tempScanner.nextLine();
        } catch (FileNotFoundException anError) {
            System.out.println(anError);
        }

        String[] tempIntervals = myInput.split(",|-");

        return tempIntervals;
    }

    static int p1(String[] anInput) {
        int tempSum = 0;

        for (int i = 0; i < anInput.length; i+=2) {
            String tempStart = anInput[i];
            String tempEnd = anInput[i+1];

            int tempStartNum = Integer.parseInt(tempStart);
            int tempEndNum = Integer.parseInt(tempEnd);

            for (int j = tempStartNum; j <= tempEndNum; j++) {
                String tempCurrentNum = Integer.toString(j);

                if (tempCurrentNum.length() % 2 == 0) {
                    int tempHalfIdx = tempCurrentNum.length() / 2;
                    String tempFirstHalf = tempCurrentNum.substring(0, tempHalfIdx);
                    String tempLastHalf = tempCurrentNum.substring(tempHalfIdx);
                    System.out.println(tempFirstHalf + " + " + tempLastHalf + " = " + tempCurrentNum);
                    if (tempFirstHalf.equals(tempLastHalf)) {
                        tempSum += j;
                    }
                }
            }
        }

        return tempSum;
    }
}
