import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        System.out.println(p2());
    }

    static int p1() {
        Dial solutionDial = processInput();
        return solutionDial.getNumZeros();
    }
    
    static int p2() {
        Dial solutionDial = processInput();
        return solutionDial.getNumPassedZero() + solutionDial.getNumZeros();
    }

    static Dial processInput() {
        Dial dial = new Dial();
        File file = new File("input");

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                boolean dir = command.charAt(0) == 'R';
                int ticks = Integer.parseInt(command.substring(1));
                if (dir) {
                    dial.right(ticks);
                } else {
                    dial.left(ticks);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return dial;
    }
}