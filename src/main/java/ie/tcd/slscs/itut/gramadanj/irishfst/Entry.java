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

import ie.tcd.slscs.itut.gramadanj.Utils;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    public String lemma;
    public String surface;
    public List<String> tags;
    public Entry() {
        tags = new ArrayList<String>();
    }
    void fromLine(String line) {
        if("++Num+Op:+".equals(line)) {
            this.lemma = "+";
            this.surface = "+";
            tags.add("+Num");
            tags.add("+Op");
        } else if(":+Punct+Int::".equals(line)) {
            this.lemma = ":";
            this.surface = ":";
            this.tags.add("+Punct");
            this.tags.add("+Int");
        } else {
            int firstPlus = line.indexOf('+');
            this.lemma = line.substring(0, firstPlus);
            int lastColon = line.lastIndexOf(':');
            String tags = line.substring(firstPlus, lastColon);
            for(String tag : tags.split("\\+")) {
                if(!"".equals(tag)) {
                    this.tags.add("+" + tag);
                }
            }
            if((lastColon + 1) == line.length()) {
                this.surface = "";
            } else {
                this.surface = line.substring(lastColon + 1);
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        final Entry e = (Entry) o;
        if(e.lemma.equals(this.lemma) && e.surface.equals(this.surface) && Utils.equalLists(e.tags, this.tags)) {
            return true;
        } else {
            return false;
        }
    }
}
