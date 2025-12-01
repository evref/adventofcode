public class Dial {
    private int currentTicks;

    public Dial() {
        currentTicks = 50;
    }

    public int getCurrentTicks() {
        return currentTicks;
    }

    private void addTicks(int someTicks) {
        currentTicks = (currentTicks + someTicks) % 100;
    }

    public void right(int someTicks) {
        addTicks(someTicks);
    }

    public void left(int someTicks) {
        addTicks(-someTicks);
    }
}
