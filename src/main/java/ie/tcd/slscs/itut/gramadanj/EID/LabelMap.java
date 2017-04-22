package ie.tcd.slscs.itut.gramadanj.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        multitmp.put("B. Hist", new String[] {"B", "Hist"});
        multitmp.put("Rugby Fb", new String[] {"Rugby"});
        multitmp.put("Meas.", new String[] {"Meas"});
        multitmp.put("Sch: etc", new String[] {"Sch"});
        multitmp.put("Rom.Ant", new String[] {"Rom", "Ant"});
        multitmp.put("Rom. Ant", new String[] {"Rom", "Ant"});
        multitmp.put("Rom. Hist", new String[] {"Rom.Hist"});
        multitmp.put("Ecc. Arch", new String[] {"Ecc.Arch"});
        multitmp.put("Nat.His", new String[] {"Nat.Hist"});
        multitmp.put("Nat.Hist: etc", new String[] {"Nat.Hist"});
        multitmp.put("Nat. Hist", new String[] {"Nat.Hist"});
        multitmp.put("Mus: etc", new String[] {"Mus"});
        multitmp.put("Pol: etc:", new String[] {"Pol"});
        multitmp.put("Typ: etc.", new String[] {"Typ"});
        multitmp.put("Typ:", new String[] {"Typ"});
        multitmp.put("W. Tel", new String[] {"W.Tel"});
        multitmp.put("Hydr.E", new String[] {"Hyd.E"});
        multitmp.put("Mec. E", new String[] {"Mec.E"});
        multitmp.put("Mech.E", new String[] {"Mec.E"});
        multitmp.put("Mch.E", new String[] {"Mec.E"});
        multitmp.put("Mech", new String[] {"Mec"});
        multitmp.put("Typwr", new String[] {"Typewr"});
        multitmp.put("I.C.E", new String[] {"I.C.E."});
        multitmp.put("Gr. alph", new String[] {"Gr.alph"});
        multitmp.put("z", new String[] {"Z"});
        multitmp.put("physiol", new String[] {"Physiol"});
        multi =  Collections.unmodifiableMap(multitmp);
        public String[] getLabel(String s, boolean EID = true) {
            if(s.equals("Alg")) {
                if(EID) {
                    return new String[] {"Algebra"};
                } else {
                    return new String[] {"Algae"};
                }
            } else {
                return multi.get(s);
            }
        }

        Map<String, String[]> postmp = new HashMap<String, String[]>();
        postmp.put("s.pl", new String[] {"s.pl."});
        postmp.put("s.pl.,", new String[] {"s.pl."});
        postmp.put("spl", new String[] {"s.pl."});
        postmp.put("a", new String[] {"a."});
        postmp.put("poss.pron", new String[] {"poss.pron."});
        postmp.put("Poss.pron.", new String[] {"poss.pron."});
        postmp.put("Poss.a.", new String[] {"poss.a."});
        postmp.put("Poss. a.", new String[] {"poss.a."});
        postmp.put("Poss. a. in Irish", new String[] {"poss.a."});
        postmp.put("s", new String[] {"s."});
        postmp.put("s.,", new String[] {"s."});
        postmp.put("s (pl.)", new String[] {"s.pl."});
        postmp.put("s.(pl.)", new String[] {"s.pl."});
        postmp.put("s.pl", new String[] {"s.pl."});
        postmp.put("s.pl.,", new String[] {"s.pl."});
        postmp.put("s. pl.", new String[] {"s.pl."});
        postmp.put("s. &  a.", new String[] {"s.", "a."});
        postmp.put("s. & a.", new String[] {"s.", "a."});
        postmp.put("a. & s", new String[] {"s.", "a."});
        postmp.put("a. & s.", new String[] {"s.", "a."});
        postmp.put("a.& s.", new String[] {"s.", "a."});
        postmp.put("a. s.", new String[] {"s.", "a."});
        postmp.put("s. & adv.", new String[] {"s.", "adv."});
        postmp.put("pred. a.", new String[] {"pred.a."});
        postmp.put("Pred. a.", new String[] {"pred.a."});
        postmp.put("pred.a", new String[] {"pred.a."});
        postmp.put("Pred.a.", new String[] {"pred.a."});
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
        addEntry("Rom.Hist", "Q646206", "/m/01_d47", "", "History of Rome");
        addEntry("Sch", "Q3914", "/m/06zdj", "Scoil", "School");
        addEntry("Mus", "Q638", "/m/04rlf", "Ceol", "Music");
        addEntry("Meas", "Q47574", "/m/02sql7", "Aonaid", "Units of measurement");
        addEntry("Ant", "Q2906114", "/m/02p49gl", "", "Antiquities");
        addEntry("Nau", "Q155930", "", "", "Ship transport"); // Nautical
        addEntry("Nat.Hist", "Q484591", "/m/01664_", "", "Natural history");
        addEntry("Pol", "Q7163", "/m/05qt0", "Polaitíocht", "Politics");
        addEntry("Pol.Ec", "Q47555", "/m/0cgx9", "", "Political economy");
        addEntry("Bill", "Q3341285", "/m/015_x", "", "Cue sports"); // billiards
        addEntry("Bootm", "Q6408486", "/m/02p39bz", "", "Shoemaking");
        addEntry("Turf", "Q184624", "/m/0pctc", "Móin", "Peat");
        addEntry("Typ", "Q159964", "/m/07p4g", "Clóghrafaíocht", "Typography");
        addEntry("Typewr", "Q46335", "/m/0c2wf", "Clóscríobhán", "Typewriter"); // typewriting
        addEntry("Fung", "Q764", "/m/03154", "Fungas", "Fungus");
        addEntry("Bot", "Q441", "/m/01bwr", "Luibheolaíocht", "Botany");
        addEntry("Bac", "Q243748", "/m/0gx21vp", "Baictéareolaíocht", "Bacteriology");
        addEntry("Bak", "Q720398", "/m/0dv34", "", "Baking");
        addEntry("Cer", "Q13464614", "", "", "Ceramic art"); // ceramics
        addEntry("N.Arch", "Q1136352", "/m/0k5qm", "", "Naval architecture");
        addEntry("Dist", "Q101017", "/m/029r1", "Driogadh", "Distillation");
        addEntry("Tex", "Q28823", "/m/0dnr7", "", "Textile");
        addEntry("Hyd.E", "Q1130265", "/m/02l42h", "", "Hydraulic_engineering");
        addEntry("I.C.E.", "Q12757", "/m/03tpm", "Inneall dócháin inmheánaigh", "Internal combustion engine");
        addEntry("Accounts", "Q4116214", "", "Cuntasaíocht", "Accounting");
        addEntry("Adm", "Q4683409", "", "", "Administration_(government)");
        addEntry("Aer", "Q8421", "", "Aerloingseoireacht", "Aeronautics");
        addEntry("Mec", "Q41217", "/m/04y7b", "Meicnic", "Mechanics");
        addEntry("Mec.E", "Q101333", "/m/04x_3", "Innealtóireacht mheicniúil", "Mechanical engineering");
        addEntry("Metall", "Q11467", "/m/04zrq", "Miotalóireacht", "Metallurgy");
        addEntry("Mil. Hist", "Q192781", "/m/050yl", "", "Military history");
        addEntry("Agr", "Q11451", "/m/0hkf", "Talmhaíocht", "Agriculture");
        addEntry("Algae", "Q37868", "/m/0hlw", "Algaí", "Algae");
        addEntry("Anat", "Q514", "/m/0hxf", "Anatamaíocht", "Anatomy");
        addEntry("Ann", "Q25522", "/m/0bvjl", "Anailid", "Annelid");
        addEntry("Ap", "Q176353", "/m/01vn35", "Beachaireacht", "Beekeeping"); // Apiculture
        addEntry("Physiol", "Q521", "/m/05wjc", "Fiseolaíocht", "Physiology");
        //addEntry("", "", "", "", "");
    }
    String[] fixMultipartTags(String s) {
        return multi.get(s);
    }

}
