package ie.tcd.slscs.gramadanj.briathra;
/*
 * Copyright 2016 Trinity College, Dublin
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

import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jaoregan on 25/10/2016.
 */
public class XMLLoaderTest {
    private static final String exampleEntry = "<eintrag lemma=\"abair\" sublemma=\"01\" gruppe=\"A\" untergruppe=\"04\" lexonomyID=\"4672\"><semantischeKlasse klasse=\"DIC\" sicherheit=\"sicher\" /><kopfzeile><paraphrase xml:space=\"preserve\">abraíonn <ph xml:space=\"preserve\">duine</ph>: \"...\"</paraphrase><übersetzungDE xml:space=\"preserve\">sagen</übersetzungDE><übersetzungEN xml:space=\"preserve\">say</übersetzungEN></kopfzeile><valenz><stelle syntaktischeRolle=\"N1\" optionalität=\"obligatorisch\"><semantischeRolle rolle=\"AGS\" sicherheit=\"sicher\" /></stelle><stelle syntaktischeRolle=\"S2\" optionalität=\"obligatorisch\"><semantischeRolle rolle=\"DIC\" sicherheit=\"sicher\" /></stelle></valenz><beispiele /><belege><beleg korpus=\"CC\" quelle=\"LC-5-02-10\" xml:space=\"preserve\">Agus nuair a bheas an taoille ardaithe ansin, ionann's chomh hard leis an gcéibh <lemma xml:space=\"preserve\">abróidh</lemma> tú:</beleg><beleg korpus=\"CC\" quelle=\"CR-6-27-07\" xml:space=\"preserve\">Dhá n-<lemma xml:space=\"preserve\">abraíodh</lemma> sé an uair sin: \"Tá píopa breá agat bail ó Dhia ort.\" Níor úirt. Ach d'úirt sé rud éigin eile roimhe sin.</beleg></belege><kommentare><kommentarIntern xml:space=\"preserve\">QU (dir)</kommentarIntern></kommentare></eintrag>";
    @Test
    public void loadXML() throws Exception {
        try {
            List<Entry> list = XMLLoader.loadXML(new InputSource(new StringReader(exampleEntry)));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}