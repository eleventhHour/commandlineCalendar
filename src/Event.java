import java.util.GregorianCalendar;

/**
 * Created by branden on 3/12/2016.
 */
public class Event {

    enum MONTHS
{
	Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
}
enum DAYS
{
	Sun, Mon, Tue, Wed, Thur, Fri, Sat ;
}
    String title;
    private GregorianCalendar eventDateStart;
    private GregorianCalendar eventDateEnd;

    public Event( String title, int year, int month, int dayOfMonth,
                  int startHour, int startMinute, int endHour, int endMinute) {
        this.title = title;
        eventDateStart = new GregorianCalendar(year, month, dayOfMonth, startHour, startMinute);
        if(endHour != -1){
         eventDateEnd = new GregorianCalendar(year, month, dayOfMonth, endHour, endMinute);
        }
    }

    public Event( String title, GregorianCalendar eventDateStart, GregorianCalendar eventDateEnd) {
        this.title = title;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
    }

    public Event ( String title, GregorianCalendar eventDateStart) {
        this.title = title;
        this.eventDateStart = eventDateStart;

    }
    public void setDate () {}

    public void getTitle() {}

    public void setTitle() {}

}
