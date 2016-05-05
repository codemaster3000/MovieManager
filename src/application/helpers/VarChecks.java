package application.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fabian
 */
public class VarChecks {

    public VarChecks() {
        // empty constructor
    }

    public boolean checkStringIsInteger(String checkString) {
        if (checkString == null) {
            return false;
        }
        for (char c : checkString.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public int getYearFromMovieFilename(String movieFilename) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher = regex.matcher(movieFilename);

        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group(1));
        }

        // first string should be year
        String year = matchList.get(0);

        // check if its valid year (4 digits)
        if (checkStringIsInteger(year)) {
            int extractedYear = Integer.parseInt(year);
            if (extractedYear > 1800 && extractedYear < 2100) {
                return extractedYear;
            }
            return 0;
        } else {
            return 0;
        }
    }
    
    public List<String> getBracketInfosFromFilename(String movieFilename) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher = regex.matcher(movieFilename);

        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group(1));
        }
        
        return matchList;
    }
}
