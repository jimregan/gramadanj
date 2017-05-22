package ie.tcd.slscs.itut.gramadanj.Conversion.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2017 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2017 Coláiste na Tríonóide, Baile Átha Cliath
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.ExceptionInInitializerError;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to load and contain data for replacement parts.
 */
final class EIDData {
    static final ClassLoader classLoader;
    private static final Map<String, Replacement> replacements;
    static {
        classLoader = EIDData.class.getClassLoader();

        try {
            replacements = readReplacements("replace-whole-entries.tsv");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private static File getFile(String name) throws Exception {
        return new File(classLoader.getResource(name).getFile());
    }

    public static Map<String, Replacement> readReplacements(File f) throws Exception {
        Map<String, Replacement> ret = new HashMap<String, Replacement>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String line;
        int lineno = 0;
        while((line = br.readLine()) != null) {
            lineno++;
            String[] sp = line.split("\\t");
            if(sp.length != 4) {
                throw new IOException("Error reading file at line: " + lineno);
            }
            String key = sp[0];
            if(!sp[1].equals("")) {
                key += "#";
                key += sp[1];
            }
            Replacement r = new Replacement(sp[0], sp[2]);
            r.addtrgs(sp[3]);
            ret.put(key, r);
        }
        return ret;
    }
    public static Map<String, Replacement> readReplacements(String name) throws Exception {
        return readReplacements(getFile(name));
    }
}
