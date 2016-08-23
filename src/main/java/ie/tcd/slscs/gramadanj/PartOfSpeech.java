package ie.tcd.slscs.gramadanj;

import org.xml.sax.InputSource;

import java.io.*;

/**
 * The PartOfSpeech class serves as a base for the classes
 * for individual parts of speech, so they can be treated
 * the same.
 */
abstract class PartOfSpeech {
    public String disambig;
    String nickname_addition;
    public String getNickname() {
        String ret = getLemma();
        ret += nickname_addition;
        if (!"".equals(this.disambig)) {
            ret += " " + this.disambig;
        }
        ret = ret.replace(" ", "_");
        return ret;
    }

    public String lemma;
    public String getLemma() {
        return lemma;
    }
    public abstract void loadXML(InputSource is) throws Exception;
    public void loadXML(InputStream is) throws Exception {
        this.loadXML(new InputSource(is));
    }
    public void loadXML(File f) throws Exception {
        this.loadXML(new FileInputStream(f));
    }
    public void loadXML(String filename) throws Exception {
        this.loadXML(new File(filename));
    }
    public abstract void writeXML(OutputStream os) throws Exception;
    public void writeXML(File f) throws Exception {
        writeXML(new FileOutputStream(f));
    }
    public void writeXML(String s) throws Exception {
        writeXML(new File(s));
    }
}
