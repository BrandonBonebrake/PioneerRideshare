package date;

public final class PioneerDate extends Date
{
    private final int MAX_MONTHS_IN_FUTURE = 3;

    public PioneerDate()
    {
        super();
    }

    public PioneerDate(int year, int month, int day) throws InvalidDateException
    {

        super();

        if(!isValidDate(year, month, day))
        {
            throw new InvalidDateException("Invalid Date");
        }
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public boolean isValidDate(int year, int month, int day)
    {
        return (((year - this.getYear() == 0) && (month - this.getMonth() <= MAX_MONTHS_IN_FUTURE)) ||
                ((year - this.getYear() == 1) && ((month + 12) - this.getMonth() <= MAX_MONTHS_IN_FUTURE)));
    }
}
