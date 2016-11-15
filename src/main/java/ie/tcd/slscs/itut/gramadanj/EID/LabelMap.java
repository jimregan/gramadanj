package ie.tcd.slscs.itut.gramadanj.EID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaoregan on 14/11/2016.
 */
public class LabelMap {
    final static Map<String, String[]> multi;
    static {
        Map<String, String[]> multitmp = new HashMap<String, String[]>();
        multitmp.put("Ecc.Arch", new String[] {"Ecc", "Arch"});
        multitmp.put("Ecc.", new String[] {"Ecc"});
        multi =  Collections.unmodifiableMap(multitmp);
    }
    private Map<String, String> wdtmp = new HashMap<String, String>();
    private Map<String, String> fbtmp = new HashMap<String, String>();
    private Map<String, String> gatmp = new HashMap<String, String>();
    private Map<String, String> entmp = new HashMap<String, String>();
    void addEntry(String lbl, String wd, String fb, String ga, String en) {
        wdtmp.put(lbl, wd);
        fbtmp.put(lbl, fb);
        gatmp.put(lbl, ga);
        entmp.put(lbl, en);
    }
    LabelMap() {
        addEntry("Pol", "Q7163", "/m/05qt0", "Polaitíocht", "Politics");
        addEntry("Ch", "Q2329", "/m/01lj9", "Ceimic", "Chemistry");
        addEntry("Ph", "Q413", "/m/05qjt", "Fisic", "Physics");
        addEntry("Arch", "Q12271", "/m/03nfmq", "Ailtireacht", "Architecture");
    }
    String[] fixMultipartTags(String s) {
        return multi.get(s);
    }

}
