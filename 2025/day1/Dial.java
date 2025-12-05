public class Dial {
    private int currentTicks;
    private int numZeros;
    private int numPassedZero;

    public Dial() {
        currentTicks = 50;
        numZeros = 0;
        numPassedZero = 0;
    }

    public int getCurrentTicks() { return currentTicks; }
    public int getNumZeros() { return numZeros; }
    public int getNumPassedZero() { return numPassedZero; }

    public void printTicks() {
        System.out.println(currentTicks);
    }

    private void addTicks(int someTicks) {
        currentTicks = (currentTicks + someTicks) % 100;
        if (currentTicks == 0) numZeros++;
    }

    public void right(int someTicks) {
        int modTicks = someTicks % 100;
        numPassedZero += (someTicks - modTicks) / 100;
        numPassedZero += currentTicks + modTicks > 100 && currentTicks != 0 ? 1 : 0;
        addTicks(modTicks);
    }

    public void left(int someTicks) {
        int modTicks = someTicks % 100;
        numPassedZero += (someTicks - modTicks) / 100;
        numPassedZero += currentTicks - modTicks < 0 && currentTicks != 0 ? 1 : 0;
        addTicks(100 - modTicks);
    }
}
