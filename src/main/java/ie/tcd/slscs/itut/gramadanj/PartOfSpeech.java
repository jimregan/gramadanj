package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

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
