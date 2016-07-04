package ie.tcd.slscs.gramadanj;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Noun {
	public String disambig = "";
	public String getNickname() {
		String ret = getLemma();
		ret += (this.getGender() == Gender.Masc ? " masc" : " fem");
		if (this.disambig != "") {
			ret += " " + this.disambig;
		}
		ret = ret.replace(" ", "_");
		return ret;
	}
	public String getFriendlyNickname() {
		String ret = getLemma();
		ret += " (";
		ret += (this.getGender() == Gender.Masc ? "masc" : "fem");
		ret += (this.declension > 0 ? this.declension : "");
		if(this.disambig != "") {
			ret += " " + this.disambig;
		}
		ret += ")";
		return ret;
	}
	
	public int declension = 0;
	public List<FormSg> sgNom = new ArrayList<FormSg>();
	public List<FormSg> sgGen = new ArrayList<FormSg>();
	public List<FormSg> sgVoc = new ArrayList<FormSg>();
	public List<FormSg> sgDat = new ArrayList<FormSg>();

	public List<Form> plNom = new ArrayList<Form>();
	public List<FormPlGen> plGen = new ArrayList<FormPlGen>();
	public List<Form> plVoc = new ArrayList<Form>();

	public List<Form> count = new ArrayList<Form>();
	
	public boolean isProper = false;
	public boolean isImmutable = false;
	public boolean isDefinite = false;
	public boolean allowArticleGenitive = false;
	
	public String getLemma() {
		String ret = "";
		Form lemmaForm = this.sgNom.get(0);
		if (lemmaForm != null) {
			ret = lemmaForm.value;
		}
		return ret;
	}
	
	public Gender getGender() {
		return this.sgNom.get(0).gender;
	}

	public void loadNoun(InputSource is) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			String root = doc.getDocumentElement().getNodeName();
			if (root != "noun") {
				System.err.println("Expected root node ");
			}
		} catch (SAXParseException sxe) {
			System.err.println("Parse error in " + sxe.getSystemId() + " at line: " + sxe.getLineNumber());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public Noun() {}
	public Noun(Gender gender, String sgNom, String sgGen, String sgVoc, 
				Strength strength, String plNom, String plGen, String plVoc) {
		this.sgNom.add(new FormSg(sgNom, gender));
		this.sgGen.add(new FormSg(sgGen, gender));
		this.sgVoc.add(new FormSg(sgVoc, gender));
		this.sgDat.add(new FormSg(sgNom, gender));
		this.plNom.add(new Form(plNom));
		this.plGen.add(new FormPlGen(plGen, strength));
		this.plVoc.add(new Form(plVoc));
	}
//	public Noun
	
}
