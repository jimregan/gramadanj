package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Based on Gramadán:
 * The MIT License (MIT)
 *
 * Copyright © 2017 Foras na Gaeilge
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
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.itut.gramadanj.Features.Gender;

public class SingularInfo {
	public Gender gender;
	public List<Form> nom;
	public List<Form> gen;
	public List<Form> dat;
	public List<Form> voc;

	SingularInfo() {
		nom = new ArrayList<Form>();
		gen = new ArrayList<Form>();
		dat = new ArrayList<Form>();
		voc = new ArrayList<Form>();
	}
	public String print() {
		String ret="";
		ret += "NOM: ";
		for (Form f : this.nom) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "GEN: ";
		for (Form f : this.gen) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "DAT: ";
		for (Form f : this.dat) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "VOC: ";
		for (Form f : this.voc) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		return ret;
	}
}
