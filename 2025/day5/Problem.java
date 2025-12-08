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


    public void printIntervals() {
        for (int i = 0; i < ingredientIntervalList.size(); i++) {
            Interval interval = ingredientIntervalList.get(i);
            System.out.println(interval.getStart() + "-" + interval.getStop());
        }
    } 
    public void print() {
        printIntervals();
        System.out.println();
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println(ingredientList.get(i));
        }
    }

    public void optimizeIntervals() {
        for (int i = 0; i < ingredientIntervalList.size(); i++) {
            Interval currentInterval = ingredientIntervalList.get(i);

            for (int j = i+1; j < ingredientIntervalList.size(); j++) {
                // Might be updated every iteration here
                Long currentStart = currentInterval.getStart();
                Long currentStop = currentInterval.getStop();

                Interval intervalToCheck = ingredientIntervalList.get(j);
                Long intervalStart = intervalToCheck.getStart();
                Long intervalStop = intervalToCheck.getStop();
                
                if ((intervalToCheck.getStart() <= currentInterval.getStop() && currentInterval.getStart() <= intervalToCheck.getStop()) ||
                    (currentInterval.getStart() <= intervalToCheck.getStop() && intervalToCheck.getStart() <= currentInterval.getStop())) {


                    Long smallestStart = currentStart < intervalStart ? currentStart : intervalStart;
                    Long largestStop = currentStop > intervalStop ? currentStop : intervalStop;

                    currentInterval.setStart(smallestStart);
                    currentInterval.setStop(largestStop);

                    ingredientIntervalList.remove(j);
                    // System.out.println("Current: " + currentStart + "-" + currentStop);
                    // System.out.println("Removing " + intervalStart + "-" + intervalStop + " and setting [" + i + "] to " + smallestStart + "-" + largestStop);
                    j = i;
                }
            }
        }
    }
}
