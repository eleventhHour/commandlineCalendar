import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by branden on 3/12/2016.
 */
public class CalendarFiler {

    /**
     * Opens a file using serialization, then returns the
     * EventList.
     *
     * @param filePath
     * @return
     */
    public static EventList openFile(String filePath) {
        String dataFile = "events.dat";
        File input = new File(dataFile);
        if (!input.exists()) {
            try {
                input.createNewFile();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile));
            EventList list = (EventList) in.readObject();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
    }

    /**
     * saves the file using serialization of the object
     * EventList
     *
     * @param filePath the path and name of file to save
     * @param list     the EventList to save
     */
    public static void saveFile(String filePath, EventList list) {

        String dataFile = "events.dat";
        File input = new File(dataFile);
        if (!input.exists()) {
            try {
                input.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fl = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fl);
            LinkedList<Event> ls = list.getAllEvents();
            for(Event e : ls){
                pw.println(e.getEventString());
            }
            pw.close();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFile));
            out.writeObject(list);
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
