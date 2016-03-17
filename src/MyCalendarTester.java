import java.util.Scanner;

public class MyCalendarTester {

    /*fields*/
   static final String calendarHeader = "Sun Mon Tue Wed Thu Fri Sat";
   static final String mainMenu = "Select one of the following options:\n" +
                     "[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit ";

    public static void main(String[] args) {

        Scanner usrInpt = new Scanner(System.in);
        while(true) {
            System.out.print(mainMenu);
            char option = usrInpt.next().toLowerCase().charAt(0);

            switch (option) {
                case 'l':
                    loadCalendar();
                    break;
                case 'v':
                    viewOption();
                    break;
                case 'c':
                    createEventOption();
                    break;
                case 'g':
                    goToEventOption();
                    break;
                case 'e':
                    printEventList();
                    break;
                case 'd':
                    deleteEventOption();
                    break;
                case 'q':
                    quitProgram();
                    break;
                default:
                    System.out.print("Not one of the options, please try again.\n\n");
            }


        }
    }

    private static void goToEventOption() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a date (MM/DD/YYYY) : ");
        String date = in.next();

    }
    private static void deleteEventOption() {
    }

    private static void createEventOption() {
        Scanner in = new Scanner(System.in);

        System.out.println( "Enter a title: ");
        String title = in.next();

        System.out.println( "Enter a date (MM/DD/YYYY) : ");
        String date = in.next();

        System.out.println( "Enter a start time (hh:mm) : ");
        String time = in.next();
   }

    private static void viewOption() {
        Scanner in = new Scanner(System.in);

        String viewByMenu = "[D]ay view or [M]onth view?";
        System.out.print(viewByMenu);
        char choice = in.next().toLowerCase().charAt(0);

        do {
         String placeHolderText = "This text in place where we should print out the day or month view\n" +
                "For example, it would print the following:\n\n" +
                "Tuesday, Mar 16, 2016\n" +
                "Branden's First Class 12:00 - 13:15\n\n";
        System.out.print(placeHolderText);

        String previousOrNextMenu = "[P]revious or [N]ext or [M]ain menu ?\n";
        System.out.print(previousOrNextMenu);
        choice = in.next().toLowerCase().charAt(0);
        }while(choice != 'm');

    }

    private static void loadCalendar() {}

    private static void printCalendar() {

    }

    void printEvent() {

    }

    private static void printEventList() {
        System.out.println("here we would print the list");
    }

    private static void quitProgram() {
        System.exit(0);
    }

    void printDayView() {}

    void printMonthView() {}
}


