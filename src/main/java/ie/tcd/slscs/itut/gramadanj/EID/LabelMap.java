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
        multitmp.put("A.Artil", new String[] {"A", "Artil"});
        multitmp.put("A.Ch", new String[] {"A", "Ch"});
        multitmp.put("A.Cost", new String[] {"A", "Cost"});
        multitmp.put("A.Fort", new String[] {"A", "Fort"});
        multitmp.put("A.Furn", new String[] {"A", "Furn"});
        multitmp.put("A.Geog", new String[] {"A", "Geog"});
        multitmp.put("A.Med", new String[] {"A", "Med"});
        multitmp.put("B.", new String[] {"B"});
        multitmp.put("B.Hist", new String[] {"B", "Hist"});
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
        addEntry("A", "Q181970", "/m/01h_l_", "", "Archaism");
        addEntry("Z", "Q431", "/m/088tb", "Zó-eolaíocht", "Zoology");
        addEntry("Fort", "Q57821", "/m/01czv3", "Daingniú", "Fortification");
        addEntry("Cost", "Q11460", "/m/09j2d", "Éadaí", "Clothing"); // "Costume"
        addEntry("Furn", "Q14745", "/m/0c_jw", "", "Furniture");
        addEntry("Num", "Q631286", "/m/09j6j", "", "Numismatics");
        addEntry("Ind", "Q8148", "/m/03rnh", "Tionscal", "Industry");
        addEntry("Mch", "Q11019", "/m/0dkw5", "Meaisín", "Machine");
        addEntry("Geog", "Q1071", "/m/034ns", "Tíreolaíocht", "Geography");
        addEntry("Med", "Q11190", "/m/04sh3", "Míochaine", "Medicine");
        addEntry("Th", "Q11635", "/m/03qsdpk", "", "Theatre");
        addEntry("B", "Q1845", "/m/015j7", "An Bíobla", "Bible");
        //addEntry("", "", "", "", "");
    }
    String[] fixMultipartTags(String s) {
        return multi.get(s);
    }

}
