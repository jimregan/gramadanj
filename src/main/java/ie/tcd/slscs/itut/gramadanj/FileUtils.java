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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * Checks if the given file name is a directory
     * @param s string containing path to directory
     * @return true if file exists, and is a directory
     */
    public static boolean isDirectory(String s) {
        File d = new File(s);
        if(d.exists() && d.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Get a list of the files in a directory
     * @param s the path to check for files
     * @return array of files in the directory
     */
    public static File[] getFileList(String s) {
        List<File> ret = new ArrayList<File>();
        if(isDirectory(s)) {
            for(File f : new File(s).listFiles()) {
                if(f.isFile()) {
                    ret.add(f);
                }
            }
        }
        File[] out = new File[ret.size()];
        out = ret.toArray(out);
        return out;
    }
    /**
     * Get a list of the files in a directory that begin with a pattern
     * @param d the path to check for files
     * @param s the pattern to check
     * @return array of files in the directory
     */
    public static File[] getFileListStartsWith(String d, String s) {
        List<File> ret = new ArrayList<File>();
        if(isDirectory(d)) {
            for(File f : new File(d).listFiles()) {
                if(f.isFile() && f.getName().startsWith(s)) {
                    ret.add(f);
                }
            }
        }
        File[] out = new File[ret.size()];
        out = ret.toArray(out);
        return out;
    }
    /**
     * Get a list of the files in a directory that end with a pattern
     * @param d the path to check for files
     * @param e the pattern to check
     * @return array of files in the directory
     */
    public static File[] getFileListEndsWith(String d, String e) {
        List<File> ret = new ArrayList<File>();
        if(isDirectory(d)) {
            for(File f : new File(d).listFiles()) {
                if(f.isFile() && f.getName().endsWith(e)) {
                    ret.add(f);
                }
            }
        }
        File[] out = new File[ret.size()];
        out = ret.toArray(out);
        return out;
    }
    /**
     * Get a list of the files in a directory that begin and end with a pattern
     * @param d the path to check for files
     * @param s the pattern to check at the start
     * @param e the pattern to check at the end
     * @return array of files in the directory
     */
    public static File[] getFileListStartsAndEndsWith(String d, String s, String e) {
        List<File> ret = new ArrayList<File>();
        if(isDirectory(d)) {
            for(File f : new File(d).listFiles()) {
                if(f.isFile() && f.getName().startsWith(s) && f.getName().endsWith(e)) {
                    ret.add(f);
                }
            }
        }
        File[] out = new File[ret.size()];
        out = ret.toArray(out);
        return out;
    }
    
    public static File[] getFileListEID(String s) {
        return getFileListStartsAndEndsWith(s, "eid", ".xml");
    }
    public static File[] getFileListFGB(String s) {
        return getFileListStartsAndEndsWith(s, "TYPESET", ".xml");
    }
}
