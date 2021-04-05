package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class provides the methods to validate data from the forms
 @author ronald
 */
public class DataValidator {

    /**
     * @author ronald
     * @param dni
     * @return true if DNI is correct
     */
    public static boolean isDNICorrect(String dni){
        boolean valid = false;

        if (dni.trim().length() != 9){
            return false;
        }

        try {
            int dniNumbers = Integer.parseInt(dni.trim().substring(0,8));
            int rest = 0;
            String letterResult = "";
            String dniLetter = dni.trim().substring(dni.length() - 1);
            String[] letters = {"T", "R", "W", "A", "G", "M", "I", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

            rest = dniNumbers % 23;
            letterResult = letters[rest];

            if (letterResult.equals(dniLetter)){
                valid = true;
            }

        } catch (Exception e){
            return false;
        }

        return valid;
    }

    /**
     * @author ronald
     * @param str
     * @return true if Name or LastName is correct
     */
    public static boolean isName_LastNameCorrect(String str){
        boolean valid = false;
        String regex = "(?i)[a-z]+$";

        if (str.matches(regex)) {
            valid = true;
        }

        return valid;
    }

    /**
     * @author ronald
     * @param strDate
     * @return true if strDate is correct date
     */
    public static boolean isValidDateStr(String strDate){
        boolean valid = false;

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        try {
            //Parsing the String
            date = formatter.parse(strDate);
            valid = true;

        } catch (ParseException e) {
            Log.warning("Date introduced is not correct");
        }

        return valid;
    }

}
