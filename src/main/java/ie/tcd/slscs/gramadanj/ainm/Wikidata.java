package ie.tcd.slscs.gramadanj.ainm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaoregan on 26/10/2016.
 */
public class Wikidata {
    static final Map<String, String> gender;
    static final Map<String, String> occupation;
    static {
        Map<String, String> gendermap = new HashMap<String, String>();
        gendermap.put("M", "Q6581097");
        gendermap.put("F", "Q6581072");
        gender = Collections.unmodifiableMap(gendermap);
        Map<String, String> occmap = new HashMap<String, String>();
        occmap.put("scoláire", "Q20826540");
        occmap.put("múinteoir", "Q37226");
        occmap.put("file", "Q49757");
        occmap.put("scríobhaí", "Q916292");
        occupation = Collections.unmodifiableMap(occmap);
    }
    public static String sex(String s) {
        if(gender.containsKey(s)) {
            return gender.get(s);
        } else {
            return "";
        }
    }
    public static String occupation(String s) {
        if(occupation.containsKey(s)) {
            return occupation.get(s);
        } else {
            return "";
        }
    }
}
