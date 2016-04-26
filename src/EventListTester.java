import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by branden on 3/20/2016.
 */
public class EventListTester implements Serializable {
    public static void main(String[] args) {
        EventList evl = new EventList();
        Event e1 = new Event("event1", new GregorianCalendar(2016, 7, 15, 12,0),
                new  GregorianCalendar(2016, 07, 15, 13, 8));
        Event e2 = new Event("event2", new GregorianCalendar(2016, 7, 15, 13,0),
                new  GregorianCalendar(2016, 07, 15, 14, 8));
        Event e3 = new Event("event3", new GregorianCalendar(2016, 8, 22, 9, 15));
        Event e4 = new Event("event4", new GregorianCalendar(2016, 8, 22, 9, 15),
                new GregorianCalendar(2016, 8, 22, 9, 45));

        System.out.println("Checking addEvent() function");
       System.out.println("Adding e1 ");
        if( evl.addEvent(e1)) {
            System.out.println("Passed, successfully added ev1");
        } else System.out.println("FAILED, did not add ev1");

        System.out.println("Adding e2");
        if(evl.addEvent(e2)) {
            System.out.println("Passed, successfully added e2");
        } else System.out.println("FAILED, did not add e2");

        System.out.println("Adding e3");
        if(evl.addEvent(e3))
            System.out.println("Passed, successgully added e3");
        else System.out.println("FAILED, did not add e3");

        System.out.println("Adding e4");
        if(evl.addEvent(e4))
            System.out.println("Passed successfully added e4");
        else System.out.println("FAILED, did not add e4");

        System.out.println("Checking hasEventOnDay");
        if(evl.hasEventOnDay(new GregorianCalendar(2016, 7, 15)))
            System.out.println("Passed, found event on 2016 7 15");
        else
            System.out.println("FAILED, did not find any evens on 2016 7 15");

        if(evl.hasEventOnDay( new GregorianCalendar(2016, 8, 22)))
            System.out.println("Passed, found event on 2016 7 15");
        else
            System.out.println("FAILED, did not find any events on 2016 7 15");

        System.out.println("Checking the getEvents method");
        LinkedList<Event> lnkdlst = evl.getEvents(new GregorianCalendar(2016, 7, 15));
        System.out.println("Printing events on 2016 7, 15");
        for( Event e : lnkdlst) {
            System.out.println(e.getEventString());
        }
        System.out.println("Printing events on 2016 8 22");
        lnkdlst = evl.getEvents(new GregorianCalendar(2016, 8, 22));
        for(Event e: lnkdlst) {
            System.out.println(e.getEventString());
        }

        System.out.println("Checking the getAllEvents");
        LinkedList<Event> list = evl.getAllEvents();
        System.out.println("Printing all events");
        for( Event e : list) {
            System.out.println(e.getEventString());
        }

        System.out.println("Checking the remove method");
        if(evl.remove(e1)) {
            if(evl.hasEvent(e1)){
                System.out.println("FAILED: remove method returned true but event still exists");
            } else {
                System.out.println("Passed: e1 successfully removed");
            }
        } else {
            System.out.println("FAILED: unable to remove event");
        }

        if(evl.remove(e3)) {
            if(evl.hasEvent(e3)) {
                System.out.println("FAILED: remove method returned true but event still exists");
            } else {
                System.out.println("Passed: e3 successfully removed");
            }
        } else {
            System.out.println("FAILED: unable to remove event e3");
        }
        System.out.println("Testing opening an empty file (Make sure to delete output.txt)");
        CalendarFiler.openFile("Event.txt");


        System.out.println("Re-adding removed elements than testing the CalendarFiler");
        if(!evl.addEvent(e1)) System.out.println("Could not add event e1");
        if(!evl.addEvent(e3)) System.out.println("Could not add event e3");

        CalendarFiler.saveFile("Event.txt",evl);
        EventList evl2 = CalendarFiler.openFile("Event.txt");

        if(evl2 == null ) {
            System.out.println("Failed to reload the file into a new list");
        }
        else System.out.println("Passed reload of file");


    }

}
