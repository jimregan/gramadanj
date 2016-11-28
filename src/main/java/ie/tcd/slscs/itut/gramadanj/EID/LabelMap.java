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
        multitmp.put("Rugby Fb", new String[] {"Rugby"});
        multi =  Collections.unmodifiableMap(multitmp);

        Map<String, String[]> postmp = new HashMap<String, String[]>();
        postmp.put("s.pl", new String[] {"s.pl."});
        postmp.put("s.pl.,", new String[] {"s.pl."});
        postmp.put("spl", new String[] {"s.pl."});
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
        addEntry("B", "Q1845", "/m/015j7", "An_Bíobla", "Bible");
        addEntry("Hist", "Q309", "/m/03g3w", "Stair", "History");
        addEntry("Bookb", "Q240471", "/m/0661ndf", "Leabharcheangal", "Bookbinding");
        addEntry("Stonew", "Q19794820", "", "", "Stonemasonry"); // "Stoneworking"
        addEntry("Book-k", "Q3707847", "/m/01h5g", "Leabharchoimeád", "Bookkeeping");
        addEntry("Civ.E", "Q77590", "/m/01r4k", "Innealtóireacht_shibhialta", "Civil_engineering");
        addEntry("W.Tel", "Q729856", "", "", "Wireless_telegraphy"); // ...and telephony
        addEntry("Ecc.Jur", "Q670732", "", "", "Religious_law");
        addEntry("Ecc.Arch", "Q47848", "/m/0b_wxk", "", "Sacred_architecture");
        addEntry("El.E", "Q43035", "/m/02lp1", "Innealtóireacht_leictreach", "Electrical_engineering");
        addEntry("Rugby", "Q5378", "/m/06bqd", "Rugbaí", "Rugby football");
        addEntry("Row", "Q159354", "/m/06f41", "Rámhaíocht", "Rowing (sport)");
        addEntry("Rh", "Q81009", "/m/06c2v", "", "Rhetoric");
        addEntry("Ropem", "", "", "", ""); // ropemaking
        addEntry("Rom.Myth", "Q122173", "/m/06k2j", "", "Roman mythology");
        addEntry("Artil", "Q64418", "/m/0_1c", "Airtléire", "Artillery");
        addEntry("Rom.Hist", "", "", "", "");
        addEntry("Sch", "Q3914", "/m/06zdj", "Scoil", "School");
        addEntry("Mus", "Q638", "/m/04rlf", "Ceol", "Music");
        //addEntry("", "", "", "", "");
    }
    String[] fixMultipartTags(String s) {
        return multi.get(s);
    }

}
