package application.helpers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author fabian
 */
public class StringStuff {

    public StringStuff() {
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
        String year = "";

        if (movieFilename.contains("(") && movieFilename.contains(")")) {
            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group(1));
            }
            year = matchList.get(0);
        } else {
            year = "x";
        }

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

    public String getMovieNameOnly(String filename) {
        String result = "";
        int pos = filename.indexOf('(', 5);
        if (pos > 0) {
            result = filename.substring(0, pos);
        } else {
            result = FilenameUtils.getBaseName(filename);
        }
        return result.trim();
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

    public int getTmdbFromNfoFile(String nfopath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(nfopath));
        String ct = new String(encoded, Charset.defaultCharset());
        int result = -1;
        
        if (ct.contains("www.themoviedb.org")){
            int startpos = ct.indexOf("www.themoviedb.org");
            int endpos = ct.indexOf("-", startpos);

            String sub1 = ct.substring(startpos, endpos);

            try{
                result = Integer.parseInt(sub1.substring(sub1.lastIndexOf("/") + 1));
            } catch (Exception ex){
                result = -1;
                System.out.println("Error: cannot parse tmdb id from nfo file " + nfopath);
            }
        }
        return result;
    }
}
