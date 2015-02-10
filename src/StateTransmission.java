
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class StateTransmission {
    
    String patternDayStart="(\\d{1,2})(th|st|nd)";
    String patternOf="of";
    String patternMonth="(january|febrauary|march|april|may|june|july|august|september|october|november|december)((,{0,1}))";
    String patternDay="^[0-9]{1,2}(,|\\s{1}|(th|st|nd)),{0,1}";
    String patternYear="(^[0-9]{2,4}|^’[0-9]{2,2})";
    String patternHolidayName="^(labor|thanksGiving|winter|christmas|newyear|memorial)";
    String patternHoliday="day|eve";
    
    Pattern p1=Pattern.compile(patternDayStart);
    Pattern p2=Pattern.compile(patternOf);
    Pattern p3=Pattern.compile(patternMonth);
    Pattern p4=Pattern.compile(patternDay);
    Pattern p5=Pattern.compile(patternHolidayName);
    Pattern p6=Pattern.compile(patternHoliday);
    Pattern p7=Pattern.compile(patternYear);
}
