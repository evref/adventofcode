import java.util.ArrayList;

public class Problem {
    private ArrayList<int[]> data;
    private String[] operators;

    public Problem() {
        data = new ArrayList<int[]>();
    }

    public void appendData(int[] dataLine) {
        data.add(dataLine);
    }
    public ArrayList<int[]> getData() { return data; }

    public void setOperators(String[] operators) { this.operators = operators; }
    public String[] getOperators() { return operators; }

    public void printData() {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(0).length; j++) {
                System.out.print(data.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
    public void printOperators() {
        for (int i = 0; i < operators.length; i++) {
            System.out.print(operators[i] + " ");
        }
        System.out.println();
    }
    public void print() {
        printData();
        printOperators();
    }
}
