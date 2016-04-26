import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

public class MyCalendarTester {

    /*fields*/
    static final String calendarHeader = " Sun Mon Tue Wed Thu Fri Sat";
    static final String mainMenu = "Select one of the following options:\n" +
            "[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit ";

    static final String filePath = "events.txt";

    enum MONTHS {
        Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
    }

    enum DAYS {
        Sun, Mon, Tue, Wed, Thur, Fri, Sat;
    }

    public static void main(String[] args) {

        Scanner usrInpt = new Scanner(System.in);
        EventList list = new EventList();
        printTodaysMonthView();

        char option = ' ';
        while (option != 'q') {

            System.out.println(mainMenu);
            String input = usrInpt.nextLine();
            option = input.toLowerCase().charAt(0);

            switch (option) {
                case 'l':
                    loadCalendar(list); //done!
                    break;
                case 'v':
                    viewOption(list, usrInpt); //done!
                    break;
                case 'c':
                    createEventOption(list, usrInpt); //done!
                    break;
                case 'g':
                    goToDayOption(list, usrInpt); //done!
                    break;
                case 'e':
                    printEventList(list); //done!
                    break;
                case 'd':
                    deleteEventOption(list, usrInpt); //done!
                    break;
                case 'q':
                    quitProgram(list);
                    break;
                default:
                    System.out.print("Not one of the options, please try again.\n\n");
            }
        }
        usrInpt.close();
    }

    private static void goToDayOption(EventList list, Scanner in) {
        int year = 0;
        int month = 1;
        int day = 2;
        int[] numDate = new int[3];

        boolean inputOk;
        do {
            System.out.println("Please enter date in MM/DD/YYYY format: ");
            String date = in.nextLine();
            inputOk = CalendarInputParser.parseDate(date, numDate);
        } while (!inputOk);

        GregorianCalendar c = new GregorianCalendar(numDate[year], numDate[month], numDate[day]);
        if ( isValidCalendarDate(c, numDate)) {
            printDayView(list, c);
        }
        else {
            System.out.println("Invalid calendar date, returning to main menu");
        }
    }

    private static void deleteEventOption(EventList list, Scanner in) {
        char choice;
        do {
            System.out.println("[S]elected or [A]ll ? ");
            String input = in.nextLine();
            choice = input.toLowerCase().charAt(0);
        } while (!(choice == 's' || choice == 'a'));

        if(choice == 'a') deleteAll(list);

        deleteSelected(list, in);


    }

    private static void deleteSelected(EventList list, Scanner in) {
        int year = 0;
        int month = 1;
        int day = 2;
        int[] numDate = new int[3];

        boolean inputOk;
        do {
            System.out.println("Please enter date in MM/DD/YYYY format: ");
            String date = in.nextLine();
            inputOk = CalendarInputParser.parseDate(date, numDate);
        } while (!inputOk);

        GregorianCalendar c = new GregorianCalendar(numDate[year], numDate[month], numDate[day]);
        if ( isValidCalendarDate(c, numDate)) {
            list.removeAllEventsOnDay(c);
        }
        else {
            System.out.println("Invalide calendar date, returning to main menu");
        }
    }

    private static void deleteAll(EventList list) {

        list.removeAll();
    }

    private static void createEventOption(EventList list, Scanner in) {

        System.out.println("Please enter title: ");
        String title = in.nextLine();
        int year = 0;
        int month = 1;
        int day = 2;
        int[] numDate = new int[3];

        int hour = 0;
        int min = 1;
        int[] numStartTime = new int[2];
        int[] numEndTime = new int[2];

        boolean inputOk;
        do {
            System.out.println("Please enter date in MM/DD/YYYY format: ");
            String date = in.nextLine();
            inputOk = CalendarInputParser.parseDate(date, numDate);
        } while (!inputOk);

        do {
            System.out.println("Please enter start time in HH:MM 24-hour clock format: ");
            String time = in.nextLine();
            inputOk = CalendarInputParser.parseTime(time, numStartTime);
        } while (!inputOk);

        String endTime;
        do {
            System.out.println("Please enter end time in HH:MM 24-hour clock format. If no end time, please leave blank. ");
            endTime = in.nextLine();
            if (!(endTime.compareTo("") == 0)) {
                inputOk = CalendarInputParser.parseTime(endTime, numEndTime);
            } else {
                inputOk = true;
            }
        } while (!inputOk);

        if (endTime.compareTo("") == 0) {
            GregorianCalendar startTimeCal = new GregorianCalendar(numDate[year], numDate[month],
                    numDate[day], numStartTime[hour], numStartTime[min]);
            if (!isValidCalendarDate(startTimeCal, numDate)) {
                System.out.println("Invalid date, returning to main menu");
            } else {
                Event e = new Event(title, startTimeCal);
                list.addEvent(e);
            }
        } else {
            GregorianCalendar startTimeCal = new GregorianCalendar(numDate[year], numDate[month],
                    numDate[day], numStartTime[hour], numEndTime[min]);
            GregorianCalendar endTimeCal = new GregorianCalendar(numDate[year], numDate[month],
                    numDate[day], numEndTime[hour], numEndTime[min]);
            if (!isValidCalendarDate(startTimeCal, numDate)) {
                System.out.println("Invalid date, returning to main menu");
            } else {
                Event e = new Event(title, startTimeCal, endTimeCal);
                list.addEvent(e);
            }
        }
    }

    private static boolean isValidCalendarDate(GregorianCalendar c, int[] dateData) {

        int year = 0;
        int month = 1;
        int day = 2;
        if (c.get(Calendar.YEAR) != dateData[year]) return false;
        if (c.get(Calendar.MONTH) != dateData[month]) return false;
        if (c.get(Calendar.DAY_OF_MONTH) != dateData[day]) return false;

        return true;
    }

    /**
     * Prints a view of the events either by day or month view
     */
    private static void viewOption(EventList list, Scanner in) {
        String viewByMenu = "[D]ay view or [M]onth view?";
        char choice;
        do {
            System.out.println(viewByMenu);
            String input = in.nextLine();
            choice = input.toLowerCase().charAt(0);
            if (choice == 'd' || choice == 'm') {

            } else {
                System.out.println("Invalid input, try again");
            }
        } while (!(choice == 'd' || choice == 'm'));

        if (choice == 'd') dayView(list, in);
        else monthView(list, in);
    }

    /**
     * calls printDayView and gets user option of previous, next or main menu
     *
     * @param list
     */
    private static void dayView(EventList list, Scanner in) {

        char choice;
        GregorianCalendar cal = new GregorianCalendar();
        cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        do {
            printDayView(list, cal);
            String previousOrNextMenu = "[P]revious or [N]ext or [M]ain menu ? ";
            System.out.println(previousOrNextMenu);
            String input = in.nextLine();
            choice = input.toLowerCase().charAt(0);
            while (!(choice == 'p' || choice == 'n' || choice == 'm')) {
                System.out.println("Invalid input, try again");
                System.out.println(previousOrNextMenu);
            }
            if (choice == 'p') {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            } else if (choice == 'n') {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        } while (!(choice == 'm'));
    }

    /**
     * Calls method to print the calendar and gets user
     * input from the user to see if they want to see the next
     * or previous calendar or return the main menu.
     *
     * @param list the event list that holds the events
     * @param in   Scanner object for getting user input
     */
    private static void monthView(EventList list, Scanner in) {
        char choice;
        GregorianCalendar cal = new GregorianCalendar();
        do {
            printMonthView(list, cal);
            String previousOrNextMenu = "[P]revious or [N]ext or [M]ain menu ?\n";
            System.out.print(previousOrNextMenu);
            choice = in.nextLine().toLowerCase().charAt(0);
            while (!(choice == 'p' || choice == 'n' || choice == 'm')) {
                System.out.println("Invalid input, try again");
                System.out.print(previousOrNextMenu);
            }
            if (choice == 'p') {
                cal.add(Calendar.MONTH, -1);
            } else if (choice == 'n') {
                cal.add(Calendar.MONTH, 1);
            }
        } while (!(choice == 'm'));
    }

    /**
     * loads the calendar from file if the file
     * exists or creates the file and a new
     * eventList object if the file does not
     * exist.
     *
     * @param list the list to put the data into.
     */
    private static void loadCalendar(EventList list) {
        list = CalendarFiler.openFile(filePath);
        if (list == null) {
            System.out.println("This is the first run");
            list = new EventList();
        }
    }

    /**
     * prints all the events in the EventList.
     *
     * @param list the event list
     */
    private static void printEventList(EventList list) {
        LinkedList<Event> allEvents = list.getAllEvents();
        int year = -1;
        for (Event e : allEvents) {
            if (e.getYear() != year) {
                year = e.getYear();
                System.out.println(year);
            }
            System.out.println(" " + e.getDate() + " " + e.getTime() + " " + e.getTitle());
        }
        System.out.println();
    }

    private static void quitProgram(EventList list) {
        CalendarFiler.saveFile(filePath,list);
        System.exit(0);
    }

    /**
     * Returns the string for the header on the day view for the
     * view option.
     *
     * @param c contains the date to print
     * @return a string of the header for the day view
     */
    private static String getDayheader(GregorianCalendar c) {
        DAYS[] arrayOfDays = DAYS.values();
        MONTHS[] arrayOfMonths = MONTHS.values();
        String dayName = String.valueOf(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1]);
        String monthName = String.valueOf(arrayOfMonths[c.get(Calendar.MONTH)]);
        int numDay = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        return dayName + ", " + monthName + " " + numDay + ", " + year;

    }

    /**
     * Prints the day view for the view option
     *
     * @param list
     * @param c
     */
    private static void printDayView(EventList list, GregorianCalendar c) {

        System.out.println(getDayheader(c));
        if (list.hasEventOnDay(c)) {
            LinkedList<Event> ls = list.getEvents(c);
            ls.peek().getDate();
            for (Event e : ls) {
                System.out.println(e.getTitle() + " " + e.getTime());

            }
        }
        System.out.println();
    }

    /**
     * Prints the month view of the calendar. Does not print
     * the heading.
     *
     * @param list the EventList with the events
     * @param c    the month with our information
     */
    private static void printMonthView(EventList list, GregorianCalendar c) {

        if (c == null) {
            c = new GregorianCalendar(); // make a default calendar with todays date
        }
        GregorianCalendar firstDayOfMonth = new GregorianCalendar(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), 1);
        MONTHS[] arrayOfMonths = MONTHS.values();

        System.out.println("        " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        System.out.println(calendarHeader);
        //pad the month, then start print each day into the calendar
        int numDayWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK);
        System.out.print(monthViewPadding(numDayWeek));
        //iterate through all the days in the calendar
        int j = numDayWeek;
        int numDaysMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= numDaysMonth; ) {
            //print the days in the week and line return after each week
            for (; j <= 7 && i <= numDaysMonth; j++) {
                if (list.hasEventOnDay(new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), i))) { //the current day
                    System.out.print(dayWithBracket(i));
                    i++;
                } else { //every other day
                    System.out.print(dayPaddingMonthView(i));
                    i++;
                }
            }
            j = 1;
            System.out.println();
        }
        System.out.println();

    }

    /**
     * used only once in the program to print the current day
     */
    private static void printTodaysMonthView() {
        GregorianCalendar c = new GregorianCalendar(); // make a default calendar with todays date
        GregorianCalendar firstDayOfMonth = new GregorianCalendar(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), 1);
        MONTHS[] arrayOfMonths = MONTHS.values();

        System.out.println("        " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
        System.out.println(calendarHeader);
        //pad the month, then start print each day into the calendar
        int numDayWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK);
        System.out.print(monthViewPadding(numDayWeek));
        //iterate through all the days in the calendar
        int j = numDayWeek;
        int numDaysMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= numDaysMonth; ) {
            //print the days in the week and line return after each week
            for (; j <= 7 && i <= numDaysMonth; j++) {
                if (i == c.get(Calendar.DAY_OF_MONTH)) { //the current day
                    System.out.print(dayWithBracket(i));
                    i++;
                } else { //every other day
                    System.out.print(dayPaddingMonthView(i));
                    i++;
                }
            }
            j = 1;
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Used for the first line of the month, givng the
     * proper spaced padding.
     *
     * @param dayOfWeek the numeric value of the day of the week. 1 = sunday, 7 = Saturday
     * @return the proper padding
     */
    private static String monthViewPadding(int dayOfWeek) {
        String spcr = "    "; //each day used four spaces
        switch (dayOfWeek) {
            case 1:
                return "";
            case 2:
                return spcr;
            case 3:
                return spcr + spcr;
            case 4:
                return spcr + spcr + spcr;
            case 5:
                return spcr + spcr + spcr + spcr;
            case 6:
                return spcr + spcr + spcr + spcr + spcr;
            case 7:
                return spcr + spcr + spcr + spcr + spcr + spcr;
            default:
                return "";
        }
    }

    /**
     * Pads the amount space for a day that will be
     * put in the month view
     *
     * @param day
     * @return
     */
    private static String dayPaddingMonthView(int day) {
        if (day < 10) {
            return "   " + day;
        }
        return "  " + day;
    }

    /**
     * Adds padding and bracket for a day that has an event.
     * Used in the month view of the calendar
     *
     * @param day
     * @return
     */
    private static String dayWithBracket(int day) {
        if (day < 10) {
            return " " + "[" + day + "]";
        }
        return "[" + day + "]";
    }

}


