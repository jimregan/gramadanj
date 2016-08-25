package ie.tcd.slscs.gramadanj;

/*
 * Copyright 2016 Jim O'Regan <jaoregan@tcd.ie>
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

import ie.tcd.slscs.gramadanj.Features.Gender;
import ie.tcd.slscs.gramadanj.Features.Strength;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

	/**
	 * Gets the value of a boolean attribute from the root node of a document
	 * @param doc Document to extract value from
	 * @param attr Name of attribute
	 * @return boolean corresponding to the value of the attribute
	 * @throws IOException
	 */
	public static boolean getBooleanAttr(Document doc, String attr) throws IOException {
	    String curattr = doc.getDocumentElement().getAttribute(attr); 
	    if(curattr == null) {
	        throw new IOException("missing attribute: " + attr);
	    } else {
            return curattr.equals("1");
        }
	}

	/**
	 * Gets the value of the gender attribute from a node.
	 * @param n Node containing the gender attribute
	 * @return Gender
	 * @throws IOException
	 */
	public static Gender getGender(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("gender").getNodeValue();
		if(attr == null) {
			throw new IOException("missing attribute: gender");
		}
		if("fem".equals(attr)) {
			return Features.Gender.Fem;
		} else if("masc".equals(attr)) {
			return Features.Gender.Masc;
		} else {
			throw new IOException("attribute gender can contain only \"masc\" or \"fem\", got: " + attr);
		}
	}
	
	/**
	 * Gets the value of the strength attribute from a node
	 * @param n Node containing the strength attribute
	 * @return Strength
	 * @throws IOException
	 */
	public static Strength getStrength(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("strength").getNodeValue();
		if(attr == null) {
			throw new IOException("missing attribute: strength");
		}
		if("strong".equals(attr)) {
			return Features.Strength.Strong;
		} else if("weak".equals(attr)) {
			return Features.Strength.Weak;
		} else {
			throw new IOException("attribute strength can contain only \"strong\" or \"weak\", got: " + attr);
		}
	}
	
	/**
	 * Gets the string of the default attribute of a node
	 * @param n Node containing the default attribute
	 * @return Value of the default attribute
	 * @throws IOException
	 */
	public static String getDefault(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("default").getNodeValue();
		if(attr == null) {
			throw new IOException("missing attribute: default");
		}
		return attr;
	}

	/**
	 * As close as I can get to Perl's s///g operator as I can get
	 */
	static String s(String text, String pattern, String replacement) {
		String ret=text;
		if (text.matches(pattern)) {
			ret=text.replaceAll(pattern, replacement);
		}
		return ret;
	}

	static <T extends Comparable<? super T>> boolean equalLists(List<T> a, List<T> b) {
		if(a == null && b == null) {
			return true;
		}
		if(a == null && b != null) {
			return false;
		}
		if(a != null && b == null) {
			return false;
		}
        if(a.size() != b.size()) {
            return false;
        }
		a = new ArrayList<T>(a);
		b = new ArrayList<T>(b);
		Collections.sort(a);
		Collections.sort(b);
		for(int i=0; i<a.size();i++) {
            // FIXME: part that fails
            boolean comparison = a.get(i).equals(b.get(i));
            System.err.println(i + " " + a.get(i).toString() + " " + b.get(i).toString() + " " + (comparison ? "true" : "false"));
            if(!comparison) {
                return false;
            }
        }
        return true;
	}
}
