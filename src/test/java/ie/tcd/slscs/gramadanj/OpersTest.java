package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;

import static ie.tcd.slscs.gramadanj.Opers.*;
import junit.framework.Assert;

public class OpersTest extends TestCase {
    public void testDemutate() throws Exception {
        assert(Demutate("bhfuil") == "fuil");
        assert(Demutate("mbó") == "bó");
        assert(Demutate("gcat") == "cat");
        assert(Demutate("ndiaidh") == "diaidh");
        assert(Demutate("tsamhradh") == "samhradh");
        assert(Demutate("dteach") == "teach");
        assert(Demutate("hasal") == "asal");
        assert(Demutate("n-asal") == "asal");
    }

    public void testMutate() throws Exception {

    }

    public void testBroaden() throws Exception {
        assert(Broaden("blaín") == "blaíon");
        assert(Broaden("blan") == "blan");
    }

    public void testDevoice() throws Exception {
        assert(Devoice("blasd") == "blast");
    }

    public void testEndsVowel() throws Exception {
        assert(EndsVowel("sneachta") == true);
        assert(EndsVowel("abair") == false);
    }

    public void testStartsVowel() throws Exception {
        assert(StartsVowel("abair") == true);
        assert(StartsVowel("sneachta") == false);
    }

    public void testUnduplicate() throws Exception {
        assertEquals("foo", Opers.Unduplicate("foo"));
        assertEquals("bar", Opers.Unduplicate("barr"));
        assertEquals("barn", Opers.Unduplicate("barn"));
    }
}