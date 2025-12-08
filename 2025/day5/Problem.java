import java.util.ArrayList;

public class Problem {
    ArrayList<Long> ingredientList;
    ArrayList<Interval> ingredientIntervalList;

    public Problem() {
        ingredientList = new ArrayList<Long>();
        ingredientIntervalList = new ArrayList<Interval>();
    }

    public void addIngredient(Long ingredientId) {
        ingredientList.add(ingredientId);
    }

    public void addInterval(Long start, Long stop) {
        ingredientIntervalList.add(new Interval(start, stop));
    }
    public void addInterval(Interval interval) {
        ingredientIntervalList.add(interval);
    }

    public void print() {
        for (int i = 0; i < ingredientIntervalList.size(); i++) {
            Interval interval = ingredientIntervalList.get(i);
            System.out.println(interval.getStart() + "-" + interval.getStop());
        }
        System.out.println();
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println(ingredientList.get(i));
        }
    }
}
