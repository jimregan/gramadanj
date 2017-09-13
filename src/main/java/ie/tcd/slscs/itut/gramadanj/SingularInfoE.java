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

public class SingularInfoE extends SingularInfo {
    public SingularInfoE(String lemma, Features.Gender gender, boolean syncope, boolean doubleDative, String slenderisationTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        String form = lemma;
        if(syncope) {
            form = Opers.Syncope(form);
        }
        form = Opers.Slenderise(form, slenderisationTarget);
        this.dat.add(new Form(lemma));
        if(doubleDative) {
            this.dat.add(new Form(form));
        }
        form = Utils.s(form, "([" + Opers.VowelsSlender + "])ngt$", "$1ngth");
        form = Utils.s(form, "ú$", "ath");
        form = form + "e";
        this.gen.add(new Form(form));
    }
    public SingularInfoE(String lemma, Features.Gender gender, boolean syncope, boolean doubleDative) {
        this(lemma, gender, syncope, doubleDative, "");
    }  
}
