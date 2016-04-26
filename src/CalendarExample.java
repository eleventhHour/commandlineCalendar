import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
enum MONTHS
{
	Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
}
enum DAYS
{
	Sun, Mon, Tue, Wed, Thur, Fri, Sat ;
}
public class CalendarExample 
{
	public static void main(String [] args)
	{
		GregorianCalendar cal = new GregorianCalendar(); // capture today
		Scanner sc = new Scanner(System.in);
		System.out.print("Today: ");
		
		printCalendar(cal);
		
		
		while (sc.hasNextLine())
		{
			String input = sc.nextLine();
			if (input.equals("p"))
			{
				cal.add(Calendar.MONTH,-1);
				printCalendar(cal);
			}
			else if (input.equals("n"))
			{
				cal.add(Calendar.MONTH,1);
				printCalendar(cal);
			}
		}
		System.out.println("Bye!");
	}
	
	public static void printCalendar(Calendar c)
	{   MONTHS[] arrayOfMonths = MONTHS.values();
	    DAYS[] arrayOfDays = DAYS.values();
	    
	    System.out.print(arrayOfDays[c.get(Calendar.DAY_OF_WEEK)-1]);
	    System.out.print(" ");
		System.out.print(arrayOfMonths[c.get(Calendar.MONTH)]);
		System.out.print(" ");
		System.out.print(c.get(Calendar.DAY_OF_MONTH));
		System.out.print(" ");
		System.out.print(c.get(Calendar.YEAR));
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), 1, 1);
        System.out.println();
        System.out.println("The month is " + arrayOfMonths[temp.get(Calendar.MONTH)]);
	    System.out.println("The first day of this month is " + arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]);
		System.out.println("Printing day of week " + temp.get(Calendar.DAY_OF_WEEK));
        System.out.println("Printing last day of month " + temp.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("Different method for first of week " + temp.getMinimum(Calendar.DAY_OF_WEEK));
        GregorianCalendar cal = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        if ( cal.hashCode() == c.hashCode() ){
            System.out.println("Hash code matches! " + cal.hashCode() + " " + c.hashCode());
        }
        else System.out.println("Hash code don't match " + cal.hashCode() + " " + c.hashCode());

	}
		
	}
