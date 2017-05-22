package ie.tcd.slscs.itut.gramadanj.Conversion.EID;
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
    final static Map<String, String[]> pos;
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
        multitmp.put("A.", new String[] {"A"});
        multitmp.put("Civ. E", new String[] {"Civ.E"});
        multitmp.put("Archaeol", new String[] {"Archeol"});
        multitmp.put("El. E", new String[] {"El.E"});
        multitmp.put("Ecc. Arch", new String[] {"Ecc.Arch"});
        multitmp.put("Ecc. Hist", new String[] {"Ecc.Hist"});
        multitmp.put("Ecc. Mus", new String[] {"Ecc.Mus"});
        multitmp.put("Hist. & F", new String[] {"Hist", "F"});
        multitmp.put("Orn:", new String[] {"Orn"});
        multitmp.put("Pol: etc:", new String[] {"Pol"});
        multitmp.put("Mus: etc", new String[] {"Mus"});
        multitmp.put("Nat. Hist", new String[] {"Nat.Hist"});
        multitmp.put("Nat.His", new String[] {"Nat.Hist"});
        multitmp.put("Nat.Hist: etc", new String[] {"Nat.Hist"});
        multitmp.put("Mec.E: etc", new String[] {"Mec.E"});
        multitmp.put("Mec.E: etc.", new String[] {"Mec.E"});
        multitmp.put("Mec: Ph: etc", new String[] {"Mec", "Ph"});
        multitmp.put("Mil: etc", new String[] {"Mil"});
        multitmp.put("Sp: etc", new String[] {"Sp"});
        multitmp.put("Tg: etc", new String[] {"Tg"});
        multitmp.put("Th: etc", new String[] {"Th"});
        multitmp.put("Tls.", new String[] {"Tls"});
        multitmp.put("Sch: etc", new String[] {"Sch"});
        multitmp.put("Rugby Fb", new String[] {"Rugby"});
        multitmp.put("Typ: etc.", new String[] {"Typ"});
        multitmp.put("Typwr", new String[] {"Typewr"});
        multitmp.put("Z:", new String[] {"Z"});
        multitmp.put("Ven: etc", new String[] {"Ven"});
        multitmp.put("Jur:", new String[] {"Jur"});
        multitmp.put("Jur: etc", new String[] {"Jur"});
        multitmp.put("Av: etc", new String[] {"Av"});
        multitmp.put("Art.", new String[] {"Art"});
        multitmp.put("Art:", new String[] {"Art"});
        multitmp.put("Art: etc", new String[] {"Art"});
        multitmp.put("A. & Adm", new String[] {"A", "Adm"});
        multitmp.put("A. & Dial", new String[] {"A", "Dial"});
        multitmp.put("A. & Lit", new String[] {"A", "Lit"});
        multitmp.put("A. & Scot", new String[] {"A", "Scot"});
        multitmp.put("A. & Tchn", new String[] {"A", "Tchn"});
        multitmp.put("Abs.", new String[] {"Abs"});
        multitmp.put("Acc.", new String[] {"Acc"});
        multitmp.put("Agr:", new String[] {"Agr"});
        multitmp.put("A.Ph", new String[] {"A", "Ph"});
        multitmp.put("A.Th", new String[] {"A", "Th"});
        multitmp.put("Boxing", new String[] {"Box"});
        multitmp.put("Metal W", new String[] {"Metalw"});
        multitmp.put("Mus-", new String[] {"Mus"});
        multitmp.put("Music", new String[] {"Mus"});
        multitmp.put("Lit:", new String[] {"Lit"});
        multitmp.put("Arch: etc", new String[] {"Arch"});
        multitmp.put("Cards: etc", new String[] {"Cards"});
        multitmp.put("Carp: etc", new String[] {"Carp"});
        multitmp.put("Dent: etc", new String[] {"Dent"});
        multitmp.put("Dress: etc", new String[] {"Dress"});
        multitmp.put("Ir. Hist", new String[] {"Ir.Hist"});
        multitmp.put("Artill", new String[] {"Artil"});
        multitmp.put("Jew", new String[] {"Jew.Rel"}); // only use is for Sabbath
        multitmp.put("Gr.alph", new String[] {"Gr.Alph"});
        multitmp.put("G.Alph", new String[] {"Gr.Alph"});
        multitmp.put("Cart", new String[] {"Mapm"});
        multitmp.put("Sm.a.", new String[] {"Sm.a"});
        multitmp.put("Constr", new String[] {"Const"});
        multitmp.put("Cricket", new String[] {"Cr"});
        multitmp.put("Cu", new String[] {"Cooking"}); // "culinary"
        multitmp.put("Cul", new String[] {"Cooking"});
        multitmp.put("B.Lit:", new String[] {"B"}); // "Biblical literature"
        multitmp.put("B.Lit", new String[] {"B"});
        multitmp.put("N. Arch", new String[] {"N.Arch"});
        multitmp.put("Book-b", new String[] {"Bookb"});
        multitmp.put("Absolutely", new String[] {"Abs"});
        multitmp.put("Basketball:", new String[] {"Basketball"});
        multitmp.put("Bot:", new String[] {"Bot"});
        multitmp.put("F.", new String[] {"F"});
        multitmp.put("Ph: El", new String[] {"Ph", "El"});
        multitmp.put("Usu.Pej", new String[] {"Pej"});
        multitmp.put("U.S.", new String[] {"U.S"});
        multitmp.put("Fen", new String[] {"Fenc"});
        multitmp.put("fig.", new String[] {"Fig"});
        multitmp.put("Eng", new String[] {"Engr"});
        multitmp.put("Gaming", new String[] {"Games"}); // don't have a better distinction
        multitmp.put("Gold-min", new String[] {"Gold-Min"});
        multitmp.put("Gr.gram", new String[] {"Gr.Gram"});
        multitmp.put("Geom.Draw", new String[] {"Geom", "Draw"});
        multitmp.put("Gr.H", new String[] {"Gr.Hist"});
        multitmp.put("Gr. Drama", new String[] {"Gr.Drama"});
        multitmp.put("St. Exch", new String[] {"St.Exch"});
        multitmp.put("Needlw", new String[] {"Needlew"});
        multitmp.put("Mth. Ph", new String[] {"Mth", "Ph"});
        multi =  Collections.unmodifiableMap(multitmp);

        Map<String, String[]> postmp = new HashMap<String, String[]>();
        postmp.put("(attrib)", new String[] {"attrib."});
        postmp.put("(attrib.)", new String[] {"attrib."});
        postmp.put("(g. id.)", new String[] {"g.id."});
        postmp.put("Adv", new String[] {"adv."});
        postmp.put("Adv. phr.", new String[] {"adv.phr."});
        postmp.put("Adv. phrs.", new String[] {"adv.phr."});
        postmp.put("Adv. use", new String[] {"adv."});
        postmp.put("Adv.phr", new String[] {"adv.phr."});
        postmp.put("Adv.phr.", new String[] {"adv.phr."});
        postmp.put("Attrib", new String[] {"attrib."});
        postmp.put("Attrib. a.", new String[] {"attrib.a."});
        postmp.put("Attrib.", new String[] {"attrib."});
        postmp.put("Attrib.a.", new String[] {"attrib.a."});
        postmp.put("Comb. fm.", new String[] {"comb.fm."});
        postmp.put("Conj.phr:", new String[] {"conj.phr."});
        postmp.put("Dem.pron.", new String[] {"dem.pron."});
        postmp.put("Poss. a. in Irish", new String[] {"poss.a."});
        postmp.put("Poss. a.", new String[] {"poss.a."});
        postmp.put("Poss.", new String[] {"poss."});
        postmp.put("Poss.a.", new String[] {"poss.a."});
        postmp.put("Poss.pron.", new String[] {"poss.pron."});
        postmp.put("Pr.n. & a.", new String[] {"Pr.n.", "a."});
        postmp.put("Pr.n.", new String[] {"Pr.n."});
        postmp.put("Pr.n.pl.", new String[] {"Pr.n.pl."});
        postmp.put("Pred. a.", new String[] {"pred.a."});
        postmp.put("Pred.a.", new String[] {"pred.a."});
        postmp.put("Prep. pron. in Irish", new String[] {"prep.pron."});
        postmp.put("Prep. pron.", new String[] {"prep.pron."});
        postmp.put("Prep.phr:", new String[] {"prep.phr."});
        postmp.put("Pron.", new String[] {"pron."});
        postmp.put("a", new String[] {"a."});
        postmp.put("a. & adv.", new String[] {"a.", "adv."});
        postmp.put("a. & pref.", new String[] {"a.", "pref."});
        postmp.put("a. & pron.", new String[] {"a.", "pron."});
        postmp.put("a. & s", new String[] {"a.", "s."});
        postmp.put("a. & s.", new String[] {"a.", "s."});
        postmp.put("a. or adv.", new String[] {"a.", "adv."});
        postmp.put("a. s.", new String[] {"a.", "s."});
        postmp.put("a.", new String[] {"a."});
        postmp.put("a.& s.", new String[] {"a.", "s."});
        postmp.put("a.& s.", new String[] {"s.", "a."});
        postmp.put("a.pl.", new String[] {"a.pl."});
        postmp.put("abbrev", new String[] {"abbrev."});
        postmp.put("abbrev.", new String[] {"abbrev."});
        postmp.put("adj. & adv.phr.", new String[] {"adj.phr.", "adv.phr."});
        postmp.put("adj. phr.", new String[] {"adj.phr."});
        postmp.put("adj.", new String[] {"a."});
        postmp.put("adj.phr.", new String[] {"adj.phr."});
        postmp.put("adv", new String[] {"adv."});
        postmp.put("adv. & a.", new String[] {"a.", "adv."});
        postmp.put("adv. & adj.", new String[] {"a.", "adv."});
        postmp.put("adv. & conj.", new String[] {"adv.", "conj."});
        postmp.put("adv. & int", new String[] {"adv.", "int."});
        postmp.put("adv. & int.", new String[] {"adv.", "int."});
        postmp.put("adv. & pred. a.", new String[] {"adv.", "pred.a."});
        postmp.put("adv. & pred.a.", new String[] {"adv.", "pred.a."});
        postmp.put("adv. & prep. a.", new String[] {"adv.", "prep.a."});
        postmp.put("adv. & prep.", new String[] {"adv.", "prep."});
        postmp.put("adv. & s.", new String[] {"adv.", "s."});
        postmp.put("adv. phr.", new String[] {"adv.phr."});
        postmp.put("adv.", new String[] {"adv."});
        postmp.put("adv., pred. a., & prep.", new String[] {"adv.", "pred.a.", "prep."});
        postmp.put("adv.phr", new String[] {"adv.phr."});
        postmp.put("adv.phr.", new String[] {"adv.phr."});
        postmp.put("attrib", new String[] {"attrib."});
        postmp.put("attrib. a. & s.", new String[] {"attrib.a.", "s."});
        postmp.put("attrib. a.", new String[] {"attrib.a."});
        postmp.put("attrib.", new String[] {"attrib."});
        postmp.put("attrib.a", new String[] {"attrib.a."});
        postmp.put("attrib.a. & adv.", new String[] {"attrib.a.", "adv."});
        postmp.put("attrib.a. & s.", new String[] {"attrib.a.", "s."});
        postmp.put("attrib.a.", new String[] {"attrib.a."});
        postmp.put("comb. fm.", new String[] {"comb.fm."});
        postmp.put("conj", new String[] {"conj."});
        postmp.put("conj. & rel. adv.", new String[] {"conj.", "rel.adv"});
        postmp.put("conj. phr.", new String[] {"conj.phr."});
        postmp.put("conj.", new String[] {"conj."});
        postmp.put("conj.phr.", new String[] {"conj.phr."});
        postmp.put("dat.", new String[] {"dat."});
        postmp.put("def. art.", new String[] {"def.art."});
        postmp.put("dem. pron.", new String[] {"dem.pron."});
        postmp.put("dem.a.", new String[] {"dem.a."});
        postmp.put("dem.adv.", new String[] {"dem.adv."});
        postmp.put("dem.pron.", new String[] {"dem.pron."});
        postmp.put("f", new String[] {"f."});
        postmp.put("f,", new String[] {"f."});
        postmp.put("f.", new String[] {"f."});
        postmp.put("f;", new String[] {"f."});
        postmp.put("g. id.", new String[] {"g.id."});
        postmp.put("g.id", new String[] {"g.id."});
        postmp.put("g.id.", new String[] {"g.id."});
        postmp.put("m", new String[] {"m."});
        postmp.put("m,", new String[] {"m."});
        postmp.put("m.", new String[] {"m."});
        postmp.put("m;", new String[] {"m."});
        postmp.put("pers. pron.", new String[] {"pers.pron."});
        postmp.put("pers.pron. pl.", new String[] {"pers.pron.pl."});
        postmp.put("pers.pron.", new String[] {"pers.pron."});
        postmp.put("phr. & s.", new String[] {"phr.", "s."});
        postmp.put("phr.", new String[] {"phr."});
        postmp.put("pl", new String[] {"pl."});
        postmp.put("pl. usu.", new String[] {"pl."});
        postmp.put("pl.", new String[] {"pl."});
        postmp.put("poss.a.", new String[] {"poss.a."});
        postmp.put("poss.pron", new String[] {"poss.pron."});
        postmp.put("poss.pron.", new String[] {"poss.pron."});
        postmp.put("pr.", new String[] {"prep."});
        postmp.put("gs.", new String[] {"gs."});
        postmp.put("pred. a.", new String[] {"pred.a."});
        postmp.put("pred.", new String[] {"pred."});
        postmp.put("pred.a", new String[] {"pred.a."});
        postmp.put("pred.a.", new String[] {"pred.a."});
        postmp.put("pref.", new String[] {"pref."});
        postmp.put("prep", new String[] {"prep."});
        postmp.put("prep. & conj.", new String[] {"prep.", "conj."});
        postmp.put("prep. & prep.phr.", new String[] {"prep.", "prep.phr."});
        postmp.put("prep. pron.", new String[] {"prep.pron."});
        postmp.put("prep.", new String[] {"prep."});
        postmp.put("pron", new String[] {"pron."});
        postmp.put("pron. & a.", new String[] {"pron.", "a."});
        postmp.put("pron. & adv.", new String[] {"pron.", "adv."});
        postmp.put("pron. & s.", new String[] {"pron.", "s."});
        postmp.put("pron.", new String[] {"pron."});
        postmp.put("rel. adv.", new String[] {"rel.adv."});
        postmp.put("rel. pron.", new String[] {"rel.pron."});
        postmp.put("rel.pron.", new String[] {"rel.pron."});
        postmp.put("s (pl.)", new String[] {"s.pl."});
        postmp.put("s", new String[] {"s."});
        postmp.put("s. &  a.", new String[] {"s.", "a."});
        postmp.put("s. & a.", new String[] {"s.", "a."});
        postmp.put("s. & adv.", new String[] {"s.", "adv."});
        postmp.put("s. & attrib.", new String[] {"s.", "attrib."});
        postmp.put("s. & attrib.a.", new String[] {"s.", "attrib.a."});
        postmp.put("s. & int.", new String[] {"s.", "int."});
        postmp.put("s. & pred. a.", new String[] {"s.", "pred.a."});
        postmp.put("s. & pron.", new String[] {"s.", "pron."});
        postmp.put("s. & v.", new String[] {"s.", "v."});
        postmp.put("s. & v.i.", new String[] {"s.", "v.i."});
        postmp.put("s. & v.tr.", new String[] {"s.", "v.tr."});
        postmp.put("s. int.", new String[] {"s.", "int."});
        postmp.put("s. or pron.", new String[] {"s.", "pron."});
        postmp.put("s. pl.", new String[] {"s.pl."});
        postmp.put("s. sg.", new String[] {"s.sg."});
        postmp.put("s.", new String[] {"s."});
        postmp.put("s.(pl.)", new String[] {"s.pl."});
        postmp.put("s., adv., & int.", new String[] {"s.", "adv.", "int."});
        postmp.put("s.,", new String[] {"s."});
        postmp.put("s.pl", new String[] {"s.pl."});
        postmp.put("s.pl.", new String[] {"s.pl."});
        postmp.put("s.pl.,", new String[] {"s.pl."});
        postmp.put("s.suff.", new String[] {"s.suff."});
        postmp.put("sg", new String[] {"sg."});
        postmp.put("sg.", new String[] {"sg."});
        postmp.put("spl", new String[] {"s.pl."});
        postmp.put("v.a.", new String[] {"v.a."});
        postmp.put("v.adj", new String[] {"v.a."});
        postmp.put("v.aux.", new String[] {"v.aux."});
        postmp.put("v.i", new String[] {"v.i."});
        postmp.put("v.i. & pr.", new String[] {"v.i.", "pr."});
        postmp.put("v.i. & pron.", new String[] {"v.i.", "pron."});
        postmp.put("v.i. & s.", new String[] {"v.i.", "s."});
        postmp.put("v.i. & tr.", new String[] {"v.i.", "v.tr."});
        postmp.put("v. t. & i.", new String[] {"v.i.", "v.tr."});
        postmp.put("v.t. & i.", new String[] {"v.i.", "v.tr."});
        postmp.put("v.i.", new String[] {"v.i."});
        postmp.put("v.impers.", new String[] {"v.impers."});
        postmp.put("v.ind. tr.", new String[] {"v.ind.tr."});
        postmp.put("v.ind.tr", new String[] {"v.ind.tr."});
        postmp.put("v.ind.tr.", new String[] {"v.ind.tr."});
        postmp.put("vn.", new String[] {"v.n."});
        postmp.put("v.n.", new String[] {"v.n."});
        postmp.put("v.pr.", new String[] {"v.pr."});
        postmp.put("v.t.", new String[] {"v.tr."});
        postmp.put("v.tr", new String[] {"v.tr."});
        postmp.put("v.tr.", new String[] {"v.tr."});
        postmp.put("(pl.)", new String[] {"pl."});
        postmp.put("Adv.", new String[] {"adv."});
        postmp.put("Usu. pl", new String[] {"pl."});
        postmp.put("Usu. pl.", new String[] {"pl."});
        postmp.put("Usually pl.", new String[] {"pl."});
        postmp.put("a. & adv. & conj.", new String[] {"a.", "adv.", "conj."});
        postmp.put("a. & adv. & s.", new String[] {"a.", "adv.", "s."});
        postmp.put("a. & s. & adv.", new String[] {"a.", "adv.", "s."});
        postmp.put("a., s. & adv.", new String[] {"a.", "adv.", "s."});
        postmp.put("adv. & s. & a.", new String[] {"a.", "adv.", "s."});
        postmp.put("adv. & indecl. s. & a.", new String[] {"a.", "adv.", "s."});
        postmp.put("a1", new String[] {"a1"});
        postmp.put("a1 & adv.", new String[] {"a1", "adv."});
        postmp.put("a1,", new String[] {"a1"});
        postmp.put("a1.", new String[] {"a1"});
        postmp.put("a2", new String[] {"a2"});
        postmp.put("a2.", new String[] {"a2"});
        postmp.put("a2,", new String[] {"a2"});
        postmp.put("a3", new String[] {"a3"});
        postmp.put("a3.", new String[] {"a3"});
        postmp.put("a3,", new String[] {"a3"});
        postmp.put("a3. & adv.", new String[] {"a3", "adv."});
        postmp.put("adv. & a3.", new String[] {"a3", "adv."});
        postmp.put("adv. & a.", new String[] {"a.", "adv."});
        postmp.put("prep., adv., a. & s.", new String[] {"prep.", "adv.", "a.", "s."});
        postmp.put("um. s. & a.", new String[] {"s.", "a."});
        postmp.put("v.t & i.", new String[] {"v.tr.", "v.i."});
        postmp.put("usually pl.", new String[] {"pl."});
        postmp.put("s. conj. & adv.", new String[] {"s.", "conj.", "adv."});
        postmp.put("s. & prep. & adv.", new String[] {"s.", "prep.", "adv."});
        postmp.put("s. & prep. & conj.", new String[] {"s.", "prep.", "conj."});
        postmp.put("pron. & a. & conj.", new String[] {"pron.", "a.", "conj."});
        postmp.put("prep. & adv.", new String[] {"prep.", "adv."});
        postmp.put("prep. & conj. & adv.", new String[] {"prep.", "conj.", "adv."});
        postmp.put("pred. a. & adv.", new String[] {"adv.", "a."});
        postmp.put("pr. n. used as s.", new String[] {"pr.n."});
        postmp.put("pr.n.", new String[] {"pr.n."});
        postmp.put("pr. n.", new String[] {"pr.n."});
        pos =  Collections.unmodifiableMap(postmp);
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
        addEntry("A", "Q181970", "/m/01h_l_", "", "Archaism");
        addEntry("Accounts", "Q4116214", "", "Cuntasaíocht", "Accounting");
        addEntry("Adm", "Q4683409", "", "", "Administration_(government)");
        addEntry("Aer", "Q8421", "", "Aerloingseoireacht", "Aeronautics");
        addEntry("Agr", "Q11451", "/m/0hkf", "Talmhaíocht", "Agriculture");
        addEntry("Algae", "Q37868", "/m/0hlw", "Algaí", "Algae");
        addEntry("Anat", "Q514", "/m/0hxf", "Anatamaíocht", "Anatomy");
        addEntry("Ann", "Q25522", "/m/0bvjl", "Anailid", "Annelid");
        addEntry("Ant", "Q2906114", "/m/02p49gl", "", "Antiquities");
        addEntry("Ap", "Q176353", "/m/01vn35", "Beachaireacht", "Beekeeping"); // Apiculture
        addEntry("Arch", "Q12271", "/m/03nfmq", "Ailtireacht", "Architecture");
        addEntry("Artil", "Q64418", "/m/0_1c", "Airtléire", "Artillery");
        addEntry("B", "Q1845", "/m/015j7", "An_Bíobla", "Bible");
        addEntry("Bac", "Q243748", "/m/0gx21vp", "Baictéareolaíocht", "Bacteriology");
        addEntry("Bak", "Q720398", "/m/0dv34", "", "Baking");
        addEntry("Bill", "Q3341285", "/m/015_x", "", "Cue sports"); // billiards
        addEntry("Book-k", "Q3707847", "/m/01h5g", "Leabharchoimeád", "Bookkeeping");
        addEntry("Bookb", "Q240471", "/m/0661ndf", "Leabharcheangal", "Bookbinding");
        addEntry("Bootm", "Q6408486", "/m/02p39bz", "", "Shoemaking");
        addEntry("Bot", "Q441", "/m/01bwr", "Luibheolaíocht", "Botany");
        addEntry("Cer", "Q13464614", "", "", "Ceramic art"); // ceramics
        addEntry("Ch", "Q2329", "/m/01lj9", "Ceimic", "Chemistry");
        addEntry("Civ.E", "Q77590", "/m/01r4k", "Innealtóireacht_shibhialta", "Civil_engineering");
        addEntry("Cost", "Q11460", "/m/09j2d", "Éadaí", "Clothing"); // "Costume"
        addEntry("Dist", "Q101017", "/m/029r1", "Driogadh", "Distillation");
        addEntry("Ecc.Arch", "Q47848", "/m/0b_wxk", "", "Sacred_architecture");
        addEntry("Ecc.Jur", "Q670732", "", "", "Religious_law");
        addEntry("El.E", "Q43035", "/m/02lp1", "Innealtóireacht_leictreach", "Electrical_engineering");
        addEntry("Fort", "Q57821", "/m/01czv3", "Daingniú", "Fortification");
        addEntry("Fung", "Q764", "/m/03154", "Fungas", "Fungus");
        addEntry("Furn", "Q14745", "/m/0c_jw", "", "Furniture");
        addEntry("Geog", "Q1071", "/m/034ns", "Tíreolaíocht", "Geography");
        addEntry("Hist", "Q309", "/m/03g3w", "Stair", "History");
        addEntry("Hyd.E", "Q1130265", "/m/02l42h", "", "Hydraulic_engineering");
        addEntry("I.C.E.", "Q12757", "/m/03tpm", "Inneall dócháin inmheánaigh", "Internal combustion engine");
        addEntry("Ind", "Q8148", "/m/03rnh", "Tionscal", "Industry");
        addEntry("Mch", "Q11019", "/m/0dkw5", "Meaisín", "Machine");
        addEntry("Meas", "Q47574", "/m/02sql7", "Aonaid", "Units of measurement");
        addEntry("Mec", "Q41217", "/m/04y7b", "Meicnic", "Mechanics");
        addEntry("Mec.E", "Q101333", "/m/04x_3", "Innealtóireacht mheicniúil", "Mechanical engineering");
        addEntry("Med", "Q11190", "/m/04sh3", "Míochaine", "Medicine");
        addEntry("Metall", "Q11467", "/m/04zrq", "Miotalóireacht", "Metallurgy");
        addEntry("Mil. Hist", "Q192781", "/m/050yl", "", "Military history");
        addEntry("Mus", "Q638", "/m/04rlf", "Ceol", "Music");
        addEntry("N.Arch", "Q1136352", "/m/0k5qm", "", "Naval architecture");
        addEntry("Nat.Hist", "Q484591", "/m/01664_", "", "Natural history");
        addEntry("Nau", "Q155930", "", "", "Ship transport"); // Nautical
        addEntry("Num", "Q631286", "/m/09j6j", "", "Numismatics");
        addEntry("Ph", "Q413", "/m/05qjt", "Fisic", "Physics");
        addEntry("Physiol", "Q521", "/m/05wjc", "Fiseolaíocht", "Physiology");
        addEntry("Pol", "Q7163", "/m/05qt0", "Polaitíocht", "Politics");
        addEntry("Pol.Ec", "Q47555", "/m/0cgx9", "", "Political economy");
        addEntry("Rh", "Q81009", "/m/06c2v", "", "Rhetoric");
        addEntry("Rom.Hist", "Q646206", "/m/01_d47", "", "History of Rome");
        addEntry("Rom.Myth", "Q122173", "/m/06k2j", "", "Roman mythology");
        addEntry("Ropem", "", "", "", ""); // ropemaking
        addEntry("Row", "Q159354", "/m/06f41", "Rámhaíocht", "Rowing (sport)");
        addEntry("Rugby", "Q5378", "/m/06bqd", "Rugbaí", "Rugby football");
        addEntry("Sch", "Q3914", "/m/06zdj", "Scoil", "School");
        addEntry("Stonew", "Q19794820", "", "", "Stonemasonry"); // "Stoneworking"
        addEntry("Tex", "Q28823", "/m/0dnr7", "", "Textile");
        addEntry("Th", "Q11635", "/m/03qsdpk", "", "Theatre");
        addEntry("Turf", "Q184624", "/m/0pctc", "Móin", "Peat");
        addEntry("Typ", "Q159964", "/m/07p4g", "Clóghrafaíocht", "Typography");
        addEntry("Typewr", "Q46335", "/m/0c2wf", "Clóscríobhán", "Typewriter"); // typewriting
        addEntry("W.Tel", "Q729856", "", "", "Wireless_telegraphy"); // ...and telephony
        addEntry("Z", "Q431", "/m/088tb", "Zó-eolaíocht", "Zoology");
        addEntry("Annals:", "Q193866", "/m/04jhm6", "", "Annals");
        addEntry("Ar", "Q11205", "/m/013sg", "Arithmetic", "Uimhríocht");
        addEntry("Arach", "Q1358", "/m/0lz1c", "Araicnid", "Arachnid");
        addEntry("Arb", "Q127213", "/m/01qfw6", "", "Arboriculture");
        addEntry("Archeol", "Q23498", "/m/0h61", "Seandálaíocht", "Archaeology");
        addEntry("Armour", "Q20793164", "", "", "Armour");
        addEntry("Art", "Q735", "/m/0jjw", "Ealaín", "Art");
        addEntry("Astr", "Q333", "/m/0dc_v", "Réalteolaíocht", "Astronomy");
        addEntry("Atom", "Q26383", "/m/0mxq", "Fisic adamhach", "Atomic physics");
        addEntry("Aut", "Q1420", "/m/0k4j", "Gluaisteán", "Car");
        addEntry("Av", "Q765633", "/m/0fzyg", "", "Aviation");
        addEntry("Ball", "Q184631", "/m/01f2jl", "Balaistíocht", "Ballistics");
        addEntry("Bank", "Q3435731", "", "", "");
        addEntry("Basketry", "Q373017", "/m/08tkfh", "", "Basket weaving");
        addEntry("El", "Q12725", "/m/02lts", "Leictreachas", "Electricity");
        addEntry("Fr.Hist", "Q7778", "", "", "History of France");
        addEntry("Gr.Hist", "Q7794", "/m/02qm81", "", "History of Greece");
        addEntry("Gr.Myth", "Q34726", "/m/034p8", "Miotaseolaíocht na Gréige", "Greek mythology");
        addEntry("Gr.Alph", "Q8216", "/m/0358gm", "Aibítir Ghréagach", "Greek alphabet");
        addEntry("Gr.Gram", "Q2886970", "/m/09yyd0", "", "Ancient Greek grammar"); // just Greek grammar, but the only use refers to Ancient Greek
        addEntry("Ph.Geog", "Q52107", "/m/05sll", "Tíreolaíocht fhisiciúil", "Physical geography");
        addEntry("Ecc.Hist", "Q846742", "/m/0wqym0w", "", "History of religions");
        addEntry("Ecc.Mus", "Q1065742", "", "", "Religious music");
        addEntry("M.Ins", "Q1356184", "/m/08lj60", "", "Marine insurance");
        addEntry("Magn", "Q3294789", "/m/04zqq", "Maighnéadas", "Magnetism");
        addEntry("Ac", "Q82811", "/m/0mx6", "Fuaimíocht", "Acoustics");
        addEntry("Acc", "Q146078", "/m/0y1h", "", "Accusative case");
        addEntry("Biol", "Q420", "/m/01540", "Bitheolaíocht", "Biology");
        addEntry("Boating", "Q2141830", "/m/03m4wn", "", "Boating");
        addEntry("Bowls", "Q895471", "/m/01chy", "", "Bowls");
        addEntry("Box", "Q32112", "/m/01cgz", "Dornálaíocht", "Boxing");
        addEntry("Ir.Hist", "Q205157", "/m/0c582", "Stair na hÉireann", "History of Ireland");
        addEntry("Jew.Rel", "Q9268", "/m/03_gx", "An Giúdachas", "Judaism");
        addEntry("Jew.Rel.H", "Q961603", "/m/01s8pk", "", "Jewish history");
        addEntry("Metalw", "Q953045", "/m/01ng72", "", "Metalworking");
        addEntry("Breed", "Q227675", "/m/04lcnzf", "", "Animal breeding");
        addEntry("Brew", "Q869095", "/m/01d_x", "Grúdaireacht", "Brewing");
        addEntry("Cards", "Q47883", "", "Culaith imeartha", "Playing card");
        addEntry("Carp", "Q203605", "/m/019x4f", "", "Carpentry");
        addEntry("Mapm", "Q42515", "/m/02296", "Cartagrafaíocht", "Cartography");
        addEntry("Chess", "Q718", "/m/01lb5", "Ficheall", "Chess");
        addEntry("Chivalry", "Q1056721", "", "", "Chivalry");
        addEntry("Fr.Geog", "Q200533", "", "", "Geography of France");
        addEntry("Cin", "Q590870", "/m/01bt7h", "Cineamatagrafaíocht", "Cinematography");
        addEntry("Coel", "Q387474", "/m/05w7c4", "Céileantráit", "Coelenterata");
        addEntry("Sm.a", "Q1058629", "/m/06xs1", "", "Small arms");
        addEntry("Coll", "Q694268", "", "", "");
        addEntry("Surv", "Q816425", "", "Suirbhéireacht", "Surveying");
        addEntry("Com", "Q26643", "/m/09s1w", "", "Commerce");
        addEntry("Conch", "Q862072", "/m/03gscf", "", "Conchology");
        addEntry("Const", "Q385378", "/m/01jnzj", "Foirgníocht", "Construction");
        addEntry("Coop", "Q2396581", "", "", ""); // cooperage
        addEntry("Cooking", "Q38695", "/m/01mtb", "Cócaireacht", "Cooking");
        addEntry("Cr", "Q5375", "/m/09xp_", "Cruicéad", "Cricket");
        addEntry("Croquet", "Q193387", "/m/01yf2", "", "Croquet");
        addEntry("Crust", "Q25364", "/m/0c2sy", "Crústach", "Crustacean");
        addEntry("Cust", "Q182290", "/m/0g46r", "", "Customs");
        addEntry("Danc", "Q11639", "/m/026bk", "Damhsa", "Dance");
        addEntry("Dent", "Q12128", "/m/0277g", "Fiaclóireacht", "Dentistry");
        addEntry("Draughts", "Q1293", "/m/0gwfm", "", "Draughts");
        addEntry("Draw", "Q2921001", "", "", ""); // drawing
        addEntry("Dressm", "Q28873655", "", "", ""); // dressmaking
        addEntry("Dy", "Q1164991", "/m/0448c1", "Ruaimneoireacht", "Dyeing");
        addEntry("Baseball", "Q5369", "/m/018jz", "Daorchluiche", "Baseball");
        addEntry("Basketball", "Q5372", "/m/018w8", "Cispheil", "Basketball");
        addEntry("Clock", "Q376", "/m/01x3z", "Clog", "Clock");
        addEntry("Clockm", "Q41767", "/m/01x2tf", "", "Horology");
        addEntry("Dice", "Q1515156", "/m/02ljkr", "", "");
        addEntry("Dom.Ec", "Q425694", "/m/0dc_f", "", "Home economics");
        addEntry("Dominoes", "Q32907", "/m/029g5", "", "Dominoes");
        addEntry("Agr.E", "Q194118", "", "", "Agricultural engineering");
        addEntry("Agr.Mch", "Q16000047", "", "", "Agricultural machinery");
        addEntry("Dial", "", "", "", ""); // dialectal form
        addEntry("Anthr", "Q23404", "/m/0h5k", "Antraipeolaíocht", "Anthropology");
        addEntry("Astrol", "Q34362", "/m/0wzm", "", "Astrology");
        addEntry("Cryst", "Q160398", "/m/025t9", "Criostalghrafaíocht", "Crystallography");
        addEntry("Abs", "", "", "", ""); // "absolute"
        addEntry("Bowls", "Q895471", "/m/01chy", "", "Bowls");
        addEntry("Brickm", "", "", "", ""); // brickmaking
        addEntry("Buddhist Rel", "Q748", "/m/092bf5", "An Búdachas", "Buddhism");
        addEntry("Drama", "Q25372", "/m/02822", "Drámaíocht", "Drama");
        addEntry("Dress", "Q11460", "/m/09j2d", "Éadaí", "Clothing");
        addEntry("Ecc.Hist", "Q4387417", "", "", "Church history");
        addEntry("El.Magn", "Q11406", "/m/02lpk", "Leictreamaighnéadas", "Electromagnetism");
        addEntry("El.Mch", "Q951504", "", "", "Electric machine");
        addEntry("El.Meas", "Q10387689", "", "", "");
        addEntry("Cy", "Q53121", "/m/01sgl", "", "Cycling");
        addEntry("Engr", "Q139106", "/m/0gc80", "", "Engraving");
        addEntry("Ent", "Q39286", "/m/0g4dc", "Feithideolaíocht", "Entomology");
        addEntry("Equit", "Q5384640", "", "", "Equitation");
        addEntry("Ethn", "Q43455", "/m/02pwq", "Eitneolaíocht", "Ethnology");
        addEntry("Exp", "Q12870", "/m/02ryw", "Substaint phléascach", "Explosive material");
        addEntry("Dynamics", "Q128030", "/m/01gyqb", "Dinimic", "Dynamics (mechanics)");
        addEntry("Pej", "Q545779", "", "", "Pejorative");
        addEntry("Tls", "Q39546", "/m/07k1x", "Uirlis", "Tool");
        addEntry("U.S", "Q30", "/m/09c7w0", "Stáit Aontaithe Mheiriceá", "United States");
        addEntry("Farr", "Q694579", "/m/03sqb_", "", "Farrier"); // "farriery" - closest match
        addEntry("Fb", "Q1081491", "", "", "Football");
        addEntry("F", "", "", "", ""); // "familiar"
        addEntry("Fenc", "Q12100", "/m/02y8z", "Pionsóireacht", "Fencing");
        addEntry("Fig", "Q5447996", "", "", "Figurative analogy");
        addEntry("Fin", "Q43015", "/m/02_7t", "", "Finance");
        addEntry("Ech", "Q44631", "/m/0bv2v", "Echinodermata", "Echinoderm");
        addEntry("Electronics", "Q11650", "/m/02mrp", "Leictreonaic", "Electronics");
        addEntry("Fish", "Q14373", "/m/094jc", "Iascaireacht", "Fishing");
        addEntry("Games", "Q11410", "/m/034s7", "Cluiche", "Game");
        addEntry("Geol", "Q1069", "/m/036hv", "Geolaíocht", "Geology");
        addEntry("Geom", "Q8087", "/m/025x7g_", "Geoiméadracht", "Geometry");
        addEntry("Glassm", "Q785222", "/m/03gvtzr", "", "Glass production");
        addEntry("Golf", "Q5377", "/m/037hz", "Galf", "Golf");
        addEntry("Gram", "Q8091", "/m/039dj", "", "Grammar");
        addEntry("Gym", "Q43450", "/m/0397w", "", "Gymnastics");
        addEntry("Hairdr", "Q55187", "/m/04pyp5", "", "Hairdresser"); // "hairdressing" - closest match
        addEntry("Handball", "Q1672856", "/m/053017", "Liathróid láimhe", "Gaelic handball");
        addEntry("Hatm", "Q663375", "/m/01dggx", "", "Hatmaking");
        addEntry("Gr.Lit", "Q1089547", "", "", "Greek literature");
        addEntry("Euph", "Q83464", "/m/02lq1", "", "Euphemism");
        addEntry("Gold-Min", "Q1071389", "/m/04qjjn", "", "Gold mining");
        addEntry("Hurling", "Q213711", "/m/0jnbx", "Iomáint", "Hurling");
        addEntry("Husb", "Q80962", "/m/01g37j", "Feirmeoireacht ainmhithe", "Animal husbandry");
        addEntry("Math", "Q395", "/m/04rjg", "Matamaitic", "Mathematics");
        addEntry("Needlew", "Q1817291", "/m/0d4cc", "", "Needlework");
        addEntry("Tennis", "Q847", "/m/07bs0", "Leadóg", "Tennis");
        addEntry("Thatching", "Q874531", "/m/0q0d2", "Ceann tuí", "Thatching");
        addEntry("Norse Myth", "Q128285", "/m/05h2k", "Miotaseolaíocht Ioruach", "Norse mythology");
        addEntry("Mountaineering", "Q36908", "/m/053f5", "Sléibhteoireacht", "Mountaineering");
        addEntry("Minting", "Q464780", "/m/022rbf", "", "Mint (facility)"); // closest match
        //addEntry("", "", "", "", "");
    }
    String[] fixMultipartTags(String s) {
        return multi.get(s);
    }

    public String[] getLabel(String s, boolean EID) {
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
    public String[] getLabel(String s) {
        return getLabel(s, true);
    }
    public static String[] getPoS(String s) {
        return pos.get(s);
    }
}
