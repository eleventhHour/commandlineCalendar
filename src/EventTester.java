import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by branden on 3/18/2016.
 */
public class EventTester {

    public static void main(String[] args) {
        String title = "CS 146 midterm";
        int year = 2016;
        int month = 2; //march b/c 0, 1, 2
        int day = 21;
        int hour = 12;
        int minute = 0;
        int endHour = 13;
        int endMinute = 15;

        Event vnt = new Event(title, year, month,
                day, hour, minute, endHour, endMinute);

        GregorianCalendar cal = new GregorianCalendar();
        System.out.print("The numeric year is ");
        System.out.println(cal.get(Calendar.YEAR));

        try {
            System.out.println(vnt.getDate());
            if (vnt.getDate().compareTo("Mon, March 21, 2016") == 0) {
                System.out.println("getDate passed");
            } else {
                System.out.println("getDate FAILED");
            }

            System.out.println(vnt.getTime());
            if (vnt.getTime().compareTo("12:00 - 13:15") == 0) {
                System.out.println("getTime passed");
            } else {
                System.out.println("getTime FAILED");
            }

            System.out.println(vnt.getTitle());
            if (vnt.getTitle().compareTo("CS 146 midterm") == 0) {
                System.out.println("getTitle passed");
            } else {
                System.out.println("getTime FAILED");
            }

            System.out.println(vnt.getEventString());
            if (vnt.getEventString().compareTo("03/21/2016: 12:00 - 13:15 CS 146 midterm") == 0) {
                System.out.println("getEventString passed");
            } else {
                System.out.println("getEventString FAILED");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("One of the test threw an exception");

        }
    }


}
