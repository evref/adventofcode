public class Interval {
    private Long start, stop;

    public Interval(Long start, Long stop) {
        this.start = start;
        this.stop = stop;
    }

    public Long getStart() { return start; };
    public void setStart(Long start) { this.start = start; };

    public Long getStop() { return stop; };
    public void setStop(Long stop) { this.stop = stop; };

}
