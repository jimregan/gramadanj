package ie.tcd.slscs.itut.gramadanj.irishfst;
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class Dumpstrings {
    private BufferedReader br;
    private String outbase;
    public Dumpstrings(String in) throws Exception {
        InputStream is = new FileInputStream(in);
        Reader reader;
        if(in.toLowerCase().endsWith(".gz")) {
            InputStream gzis = new GZIPInputStream(is);
            reader = new InputStreamReader(gzis);
            outbase = in.substring(0, in.length() - 3);
        } else {
            reader = new InputStreamReader(is);
            outbase = in;
        }
        this.br = new BufferedReader(reader);
    }

    List<Entry> getEntries() {
        List<Entry> ret = new ArrayList<Entry>();
        String line;
        while(line = br.readLine()) {
            Entry e = new Entry();
            e.fromLine(line);
            if(!e.surface.equals("") && !Apertium.isExcluded(e)) {
                ret.add(e);
            }
        }
        return ret;
    }
}
