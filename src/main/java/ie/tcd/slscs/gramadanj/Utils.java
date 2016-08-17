package ie.tcd.slscs.gramadanj;

import java.io.IOException;

import org.w3c.dom.Document;

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

}
