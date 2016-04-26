import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by branden on 3/20/2016.
 */
public class CalendarInputParserTester {
    public static void main(String[] args){
        System.out.println("Testing parseDate ");
        int[] date = new int[3];

//        String someStr = "AABB/";
//
//
//        if(someStr.matches("[A-Za-z!@#$%&*:;]")) { System.out.println("Found");}
//        else System.out.println("Not Found");
//        if(CalendarInputParser.parseDate("03/16/2016",date)){
//            System.out.println("PASSED, able to parse 03/16/2016");
//            System.out.println("Parsed " + date[1] + " " + date[2] + " " + date[0]);
//        } else { System.out.println("FAILED, should have parsed 03/16/2016 ***********");}

        if(CalendarInputParser.parseDate("AA/BB/2016", date)){
            System.out.println("FAIL, should have not parsed AA/BB/2016 ************");
        } else {
            System.out.println("PASSED, was not able to parse AA/BB/2016");
        }

        if(CalendarInputParser.parseDate("-1", date)){
            System.out.println("FAILED, should not have parsed -1 *************");

        } else {
            System.out.println("PASSED, was not able to parse -1");
        }

        if(CalendarInputParser.parseDate("13/05/2016", date)){
            System.out.println("FAILED, should not have parsed 13/05/2016 *********");
        } else {
            System.out.println("PASSED, was not able to parse 13/05/2016");
        }

        //check parseTime

        String[] str1 = {"08:25", "12:59", "00:00", "01:59", "23:59"};
        for(String st : str1) {
            if (parseTimeChecker(st)) {
                System.out.println("Passed: " + st + " was parsed");
            } else {
                System.out.println("FAILED: " + st + " should have been parsed");
            }
        }

        //now for failing strings
        String[] str2 = {"AA:10", "20:B0", "[]:25", "25:30", "24:61", "\\", "*8:01","0812:", "0812"};
        for (String st : str2) {
            if(parseTimeChecker(st)) {
                System.out.println("FAILED: " + st + " should not have been parsed");
            }
            else {
                System.out.println("Passed: " + st + " did not parse");
            }
        }

    }

    private static boolean parseTimeChecker(String str2check)
    {
        int[] time = new int[2];
        return CalendarInputParser.parseTime(str2check, time);
    }
}
