import java.util.Scanner;

public class MyCalendarTester {

    /*fields*/
   static final String calendarHeader = "Sun Mon Tue Wed Thu Fri Sat";
   static final String mainMenu = "Select one of the following options:\n" +
                     "[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit";
    static final String viewByMenu = "[D]ay view or [M]onth view?";

    public static void main(String[] args) {

        Scanner usrInpt = new Scanner(System.in);

        System.out.print(mainMenu);
        char option = usrInpt.next().toLowerCase().charAt(0);

        switch(option) {
            case 'L': loadCalendar();
                      break;
            case 'v':
        }


    }

    void printCalendar() {

    }

    void printEvent() {

    }

    void printEventList() {}

    void quitProgram() {}

    void printDayView() {}

    void printMonthView() {}
}


