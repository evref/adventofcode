import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] tempInput = processInput();
        Long tempAnswer = p2(tempInput);
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

    static Long p1(String[] anInput) {
        Long tempSum = 0L;

        for (int i = 0; i < anInput.length; i+=2) {
            String tempStart = anInput[i];
            String tempEnd = anInput[i+1];
            System.out.println(tempStart + " - " + tempEnd);

            Long tempStartNum = Long.parseLong(tempStart);
            Long tempEndNum = Long.parseLong(tempEnd);

            for (Long j = tempStartNum; j <= tempEndNum; j++) {
                String tempCurrentNum = Long.toString(j);

                if (tempCurrentNum.length() % 2 == 0) {
                    int tempHalfIdx = tempCurrentNum.length() / 2;
                    String tempFirstHalf = tempCurrentNum.substring(0, tempHalfIdx);
                    String tempLastHalf = tempCurrentNum.substring(tempHalfIdx);
                    if (tempFirstHalf.equals(tempLastHalf)) {
                        tempSum += j;
                    }
                }
            }
        }

        return tempSum;
    }

    static Long p2(String[] anInput) {
        Long tempSum = 0L;

        for (int i = 0; i < anInput.length; i+=2) {
            String tempStart = anInput[i];
            String tempEnd = anInput[i+1];
            System.out.println(tempStart + " - " + tempEnd);

            Long tempStartNum = Long.parseLong(tempStart);
            Long tempEndNum = Long.parseLong(tempEnd);

            for (Long j = tempStartNum; j <= tempEndNum; j++) {
                String tempCurrentNum = Long.toString(j);

                for (int k = 1; k < tempCurrentNum.length()/2+1; k++) {
                    if (tempCurrentNum.length() % k != 0) continue;

                    boolean tempSubstringsAreEven = true;
                    for (int m = 0; m < tempCurrentNum.length() / k - 1; m++) {
                        String tempFormerSubNum = tempCurrentNum.substring(k*m, k*(m+1));
                        String tempLatterSubNum = tempCurrentNum.substring(k*(m+1), k*(m+2));
                        tempSubstringsAreEven = tempFormerSubNum.equals(tempLatterSubNum);

                        if (!tempSubstringsAreEven) {
                            break;
                        }
                    }

                    if (tempSubstringsAreEven) {
                        tempSum += j;
                        break;
                    }
                }
            }
        }

        return tempSum;
    }
}
