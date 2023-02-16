public class DateTime extends Date{
    private final int hour;
    private final int minute;
    private final int second;

    public DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException {
        super(year, month, day);
        if(hour>24 || hour<0 || minute>59 || minute<0 || second>59 || second<0) {
            throw new DateException(String.format("Illegal time %02d:%02d:%02d", hour, minute, second));
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %02d:%02d:%02d", hour, minute, second);
    }
}
