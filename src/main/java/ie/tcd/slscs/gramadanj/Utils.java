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
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;

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
	        if(curattr.equals("1")) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	}

	/**
	 * Gets the value of the gender attribute from a node.
	 * @param n Node containing the gender attribute
	 * @return Gender
	 * @throws IOException
	 */
	public static Gender getGender(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("gender").toString();
		if(attr == null) {
			throw new IOException("missing attribute: gender");
		}
		if(attr == "fem") {
			return Gender.Fem;
		} else if(attr == "masc") {
			return Gender.Masc;
		} else {
			throw new IOException("attribute gender can contain only \"masc\" or \"fem\"");
		}
	}
	
	/**
	 * Gets the value of the strength attribute from a node
	 * @param n Node containing the strength attribute
	 * @return Strength
	 * @throws IOException
	 */
	public static Strength getStrength(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("strength").toString();
		if(attr == null) {
			throw new IOException("missing attribute: strength");
		}
		if(attr == "strong") {
			return Strength.Strong;
		} else if(attr == "weak") {
			return Strength.Weak;
		} else {
			throw new IOException("attribute strength can contain only \"strong\" or \"weak\"");
		}
	}
	
	/**
	 * Gets the string of the default attribute of a node
	 * @param n Node containing the default attribute
	 * @return Value of the default attribute
	 * @throws IOException
	 */
	public static String getDefault(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("default").toString();
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
}
