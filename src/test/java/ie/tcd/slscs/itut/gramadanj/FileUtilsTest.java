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

import junit.framework.TestCase;
import ie.tcd.slscs.itut.gramadanj.Utils;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class FileUtilsTest extends TestCase {
    static final String systmp;
    static final String tmpdir;
    static final String[] filenames = new String[]{
        "test1.txt", "test2.dat", "text1.txt"
    };
    static {
        systmp = System.getProperty("java.io.tmpdir");
        if(systmp.endsWith(File.separator)) {
            tmpdir = systmp + "fileutilstest";
        } else {
            tmpdir = systmp + File.separator + "fileutilstest";
        }
    }

    @Override
    protected void setUp() throws Exception {
        File dir = new File(tmpdir);
        if(!dir.mkdir()) {
            throw new Exception("Failed to create directory for test");
        }
        for (String s : filenames) {
            File f = new File(dir.getAbsolutePath() + File.separator + s);
            if(!f.createNewFile()) {
                throw new Exception("Failed to create file: " + s);
            }
        }
    }

    public void testIsDirectory() {
        assertEquals(FileUtils.isDirectory(tmpdir), true);
        assertEquals(FileUtils.isDirectory(tmpdir + File.separator + "doesnotexist"), false);
    }
    public void testGetFileList() {
        File[] files = FileUtils.getFileList(tmpdir);
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        assertEquals(files.length, filenames.length);
        Arrays.sort(names);
        assertArrayEquals(names, filenames);
    }
    public void testGetFileListStartsWith() {
        File[] files = FileUtils.getFileListStartsWith(tmpdir, "test");
        String[] exp = new String[]{
            "test1.txt", "test2.dat"
        };

        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        assertEquals(files.length, exp.length);
        Arrays.sort(names);
        assertArrayEquals(names, exp);
    }
    public void testGetFileListEndsWith() {
        File[] files = FileUtils.getFileListEndsWith(tmpdir, ".txt");
        String[] exp = new String[]{
        "test1.txt", "text1.txt"
        };

        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        assertEquals(files.length, exp.length);
        Arrays.sort(names);
        assertArrayEquals(names, exp);
    }
    public void testGetFileListStartsAndEndsWith() {
        File[] files = FileUtils.getFileListStartsAndEndsWith(tmpdir, "test", ".txt");
        String[] exp = new String[]{
        "test1.txt"
        };

        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        assertEquals(files.length, exp.length);
        Arrays.sort(names);
        assertArrayEquals(names, exp);
    }

    @Override
    protected void tearDown() throws Exception {
        File dir = new File(tmpdir);
        for (String s : filenames) {
            File f = new File(dir.getAbsolutePath() + File.separator + s);
            if(!f.delete()) {
                throw new Exception("Failed to delete file: " + s);
            }
        }
        if(!dir.delete()) {
            throw new Exception("Failed to remove temporary directory");
        }
    }
}
