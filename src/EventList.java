import java.io.Serializable;
import java.util.*;

/**
 * Created by branden on 3/12/2016.
 * A class to handle storing the events.
 * Organizes them by year, month, day.
 */
public class EventList implements Serializable {

    private HashMap<Integer, LinkedList<Event>> hm;

    /**
     * Constructs a default event list
     */
    public EventList() {

        hm = new HashMap<Integer, LinkedList<Event>>();
    }

    public LinkedList<Event> getAllEvents() {
        LinkedList<Event> list = new LinkedList<Event>();
        for(HashMap.Entry<Integer, LinkedList<Event>> map : hm.entrySet()) {
           list.addAll(map.getValue());
        }
        //provide a deep copy of the list make our internal list immutable
        LinkedList<Event> deepCopyList = new LinkedList<Event>();
        for( Event e : list ) {
            deepCopyList.add(e.Clone());
        }
        return deepCopyList;
    }

    /**
     * Returns true of false whether an event exists
     *
     * @param e the event
     * @return true or false the event is in the list
     */
    public boolean hasEvent(Event e) {

        int hCode = e.hashCode();
        if (hm.containsKey(hCode)) {
            return hm.get(hCode).contains(e);
        }
        return false;
    }

    /**
     * returns true or false whether an event exist on the
     * given day.
     *
     * @param c GregorianCalendar object of the day in question
     * @return true or false whether the event exists
     */
    public boolean hasEventOnDay(GregorianCalendar c) {

        return hm.containsKey(c.hashCode());
    }

    /**
     * Returns a list of the events on a given day.
     * User should check if the element exist first,
     * but if the no events exists on the given day
     * then return null.
     *
     * @return a list of events or null if no events exist on the given day
     */
    public LinkedList<Event> getEvents(GregorianCalendar c) {
        int hCode = c.hashCode();
        if(hasEventOnDay(c)) {
            LinkedList<Event> list = new LinkedList<Event>();
            LinkedList<Event> ls = hm.get(hCode);
            for(Event e : ls) {
                list.add(e.Clone());
            }
            return list;
        } else { return null; }

    }

    /**
     * adds an event to the EventList and
     * returns true if the event is successfully
     * added or false if the event already exist
     * and was not added.
     *
     * @param e the Event object to be added
     * @return true if the event was added, false if not
     */
    public boolean addEvent(Event e) {

        int hCode = e.hashCode();
        if (hm.containsKey(hCode)) { //check if a day already has an event
            LinkedList<Event> ll = hm.get(hCode);
            if (!inList(e, ll)) { //check if the event is unique, if it is add it
                addToList(e, ll);
                return true;
            } else {
                return false;
            } //event already in the list
        } else { //no event in the hashmap, so create new list with the event and place into hashmap
            LinkedList<Event> list = new LinkedList<Event>();
            list.add(e);
            hm.put(hCode, list);
            return true;
        }
    }

    /**
     * If there is a collision in the hashMap, checks
     * if the Event is already stored or if this is a
     * unique event. If the event is unique, then returns
     * false. If event is already in the list, then returns
     * true.
     *
     * @param e the event we want to check if it is in the list
     * @param L the list from the hashmap
     * @return whether the event exists or not
     */
    private boolean inList(Event e, LinkedList<Event> L) {
        for (Event ev : L) {
            if (e.equals(ev)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds the object to the list in order of time
     *
     * @param e the event to be added
     * @param l the list the event is to be added to
     */
    private void addToList(Event e, LinkedList<Event> l) {

        l.add(e);
        Collections.sort(l);

    }

    /**
     * Removes an event from the list.
     *
     * @param e The event to remove
     * @return true if the event is removed, false if the event didn't exist to be removed
     */
    public boolean remove(Event e) {

        if( hasEvent(e)) {
            int hCode = e.hashCode();
            LinkedList<Event> list = hm.get(hCode);
            return list.remove(e);
        }
        return false;
    }

    /**
     * removes all events on a given day.
     * @param c
     */
    public void removeAllEventsOnDay(GregorianCalendar c){
        int hCode = c.hashCode();
        hm.remove(hCode);
    }

    /**
     * removes all events in the EventList.
     */
    public void removeAll() {
        hm = new HashMap<Integer, LinkedList<Event>>();
    }
}
