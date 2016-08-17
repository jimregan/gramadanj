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

public class Utils {

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
	
	public static String getDefault(Node n) throws IOException {
		String attr = n.getAttributes().getNamedItem("default").toString();
		if(attr == null) {
			throw new IOException("missing attribute: default");
		}
		return attr;
	}
}
