import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by branden on 3/12/2016.
 */
public class Event {

    enum MONTHS {
        Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat;
    }

    String title;
    private GregorianCalendar eventDateStart;
    private GregorianCalendar eventDateEnd;

    /**
     * Constructs an Event object with the title of the event, the date (year, month, day)
     * and the start and end time by the hour and minute.
     *
     * @param title       the title of the event
     * @param year        the numeric year of the event
     * @param month       the numeric month of the event
     * @param dayOfMonth  the numeric day of the month
     * @param startHour   the numeric start hour in 24h format HH
     * @param startMinute the numeric start minute MM
     * @param endHour     the numeric end hour in 24h hour format HH
     * @param endMinute   the numeric end minute MM
     */
    public Event(String title, int year, int month, int dayOfMonth,
                 int startHour, int startMinute, int endHour, int endMinute) {
        this.title = title;
        eventDateStart = new GregorianCalendar(year, month, dayOfMonth, startHour, startMinute);
        if (endHour != -1) {
            eventDateEnd = new GregorianCalendar(year, month, dayOfMonth, endHour, endMinute);
        }
    }

    /**
     * Constructs an Event object using the title and a gregorian
     * calendar object with the start time and a gregoriancalendar
     * using the end time.
     *
     * @param title          The title of the event
     * @param eventDateStart a calendar object with the start date and time in HH:MM
     * @param eventDateEnd   a calendar object with the end date and time in HH:MM
     */
    public Event(String title, GregorianCalendar eventDateStart, GregorianCalendar eventDateEnd) {
        this.title = title;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
    }

    /**
     * Construct an Event object with the title and a
     * GregorianCalendar object with the event start date.
     * This constructor is used when an event only has
     * a start time but no end time.
     *
     * @param title          the title of the event
     * @param eventDateStart a calendar object with the
     *                       start date and time in HH:MM
     */
    public Event(String title, GregorianCalendar eventDateStart) {
        this.title = title;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = null;

    }

    /**
     * Return the abbreviated name of the day, the name of the
     * month, the day, and the year as a string. The format is as follows:
     * Day, Month DD, YYYY
     * <p/>
     * For example an event might be "Fri, March 21, 2016"
     *
     * @return String of the full date of the event not including the time
     */
    public String getDate() {

        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        String month = String.valueOf(arrayOfMonths[eventDateStart.get(Calendar.MONTH)]);
        String nameDay = String.valueOf(arrayOfDays[eventDateStart.get(Calendar.DAY_OF_WEEK)-1]);

        String numDay = Integer.toString(eventDateStart.get(Calendar.DAY_OF_MONTH));
        String year = Integer.toString(eventDateStart.get(Calendar.YEAR));

        return nameDay + ", " + month + " " + numDay + ", " + year;
    }

    /**
     * Returns the title of the event.
     *
     * @return String of the event title
     */
    public String getTitle() {
        return title;
    }

    /**
     * gets the time for the event. If the event has both
     * a start and end time, return the string as
     * HH:MM - HH:MM. If there is only a start time,
     * then it returns only the start time.
     *
     * @return String of the event time as HH:MM - HH:MM
     */
    public String getTime() {

        String startHour = addLeadingZero(eventDateStart.get(Calendar.HOUR_OF_DAY));
        String startMin = addLeadingZero(eventDateStart.get(Calendar.MINUTE));
        if (eventDateEnd != null) {
            String endHour = addLeadingZero(eventDateEnd.get(Calendar.HOUR_OF_DAY));
            String endMin = addLeadingZero(eventDateEnd.get(Calendar.MINUTE));
            return startHour + ":" + startMin + " - " + endHour + ":" + endMin;

        }

        return startHour + ":" + startMin;

    }

    String addLeadingZero(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return Integer.toString(number);
        }
    }

    /**
     * creates a string for the file save
     *
     * @return a string with all the event details in a single string
     */
    public String getEventString() {

        String numMonth = addLeadingZero(eventDateStart.get(Calendar.MONTH)+1);
        String numDay = addLeadingZero(eventDateStart.get(Calendar.DAY_OF_MONTH));
        String year = addLeadingZero(eventDateStart.get(Calendar.YEAR));
        return numMonth + "/" + numDay + "/" + year + ": " +
                getTime() + " " + getTitle();
    }
}
