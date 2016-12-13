#!/usr/bin/perl
# Copyright (c) 2016 Trinity College, Dublin
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

use warnings;
use strict;

while(<>) {
    # Content outside of <label>
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#g;
    # Specific fixes
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    s#<label>Cinceasú</label> <label>m</label>#<trg>Cinceasú <label>m</label></trg>#;
    s#<trg>dian <noindex>\(<label>to, towards</label>, ar\)</noindex></trg>#<trg>dian</trg> <noindex>(<src>to, towards</src>, <trg>ar</trg>)</noindex>#;
    # Genitive with gender
    s#<label>([mf]) -([^<]*)</label></trg>#<label>$1</label> -$2</trg>#g;

    # label outside of trg
    s#<trg>([^<]*)</trg> <label>([mf])</label> <trg>\(([^<]*)\)</trg>#<trg>$1 <label>$2</label> ($3)</trg>#g;
    s#<trg>([^<]*)</trg> <label>([mf])</label>#<trg>$1 <label>$2</label></trg>#g;
    # a second noun and its gender attached to the first noun's gender
    s#<label>([mf]), ([^ ]*) ([mf])</label></trg>#<label>$1</label></trg>, <trg>$2 <label>$3</label></trg>#g;
    s#<label>([mf]), ([^ ]*) ([mf])\.</label></trg>#<label>$1</label></trg>, <trg>$2 <label>$3</label></trg>.#g;
    # Conversion error: put into <trg> instead of <label>
    s#<label>([^ ]*) &lt;([mf])</label> ([^&]*)&gt;#<trg>$1 <label>$2</label> $3</trg>#g;

    # want to keep the ')' with the disambiguating context with which it belongs, to not include that information as a translation
    s#etc</trg>\.\)\.#etc.)</trg>.#g;
    # fix combination of <label> and unmarked that ought to have been <srg> and <trg>
    s# <noindex>\(<label>([^<]*)</label>, ([^)]*)\)</noindex></trg>#</trg> <noindex>(<src>$1</src>, <trg>$2</trg>)</noindex>#g;
    # similar to the above, seems to be either a conversion error, or one part was missing.
    s#<label>([^<]*)</label> \(<src>([^<]*)</src>, \[([^<]*)</trg>\)#<label>$1</label></trg> <noindex>(<src>$2</src>, <trg>$3</trg>)</noindex>#g;
    # rejoin pieces of disambiguating context that were split (the second part is *not* a translation)
    s#\(([^<]*)</trg>, <trg>([^<]*)</trg>\.\)\.#($1, $2.)</trg>.#g;

    # Split two combined entries into separate <trg>
    s!<trg>([^ ]*) <label>([mf])</label>([\.,]) ([^ ]*) <label>([mf])</label></trg>!<trg>$1 <label>$2</label></trg>$3 <trg>$4 <label>$5</label></trg>!;

    # Separate joined part-of-speech & domain label into individual labels
    s!<label>a. &amp; s. Geog</label>!<label>a. &amp; s.</label> <label>Geog</label>!;
    s!<label>a. Bot:</label>!<label>a.</label> <label>Bot:</label>!;
    s!<label>a. Ch</label>!<label>a.</label> <label>Ch</label>!;
    s!<label>a. Com</label>!<label>a.</label> <label>Com</label>!;
    s!<label>a. El.E</label>!<label>a.</label> <label>El.E</label>!;
    s!<label>a. Laund</label>!<label>a.</label> <label>Laund</label>!;
    s!<label>s. A</label>!<label>s.</label> <label>A</label>!;
    s!<label>s. Anat</label>!<label>s.</label> <label>Anat</label>!;
    s!<label>s. Archeol</label>!<label>s.</label> <label>Archeol</label>!;
    s!<label>s. Bot</label>!<label>s.</label> <label>Bot</label>!;
    s!<label>s. Box</label>!<label>s.</label> <label>Box</label>!;
    s!<label>s. Brew</label>!<label>s.</label> <label>Brew</label>!;
    s!<label>s. Cards</label>!<label>s.</label> <label>Cards</label>!;
    s!<label>s. Civ.E</label>!<label>s.</label> <label>Civ.E</label>!;
    s!<label>s. Cost</label>!<label>s.</label> <label>Cost</label>!;
    s!<label>s. Cr</label>!<label>s.</label> <label>Cr</label>!;
    s!<label>s. Ecc</label>!<label>s.</label> <label>Ecc</label>!;
    s!<label>s. El</label>!<label>s.</label> <label>El</label>!;
    s!<label>s. Ent</label>!<label>s.</label> <label>Ent</label>!;
    s!<label>s. F</label>!<label>s.</label> <label>F</label>!;
    s!<label>s. Fin</label>!<label>s.</label> <label>Fin</label>!;
    s!<label>s. Geog</label>!<label>s.</label> <label>Geog</label>!;
    s!<label>s. Harn</label>!<label>s.</label> <label>Harn</label>!;
    s!<label>s.pl. Harn</label>!<label>s.pl.</label> <label>Harn</label>!;
    s!<label>s. Her</label>!<label>s.</label> <label>Her</label>!;
    s!<label>s. Hockey</label>!<label>s.</label> <label>Hockey</label>!;
    s!<label>s. Husb</label>!<label>s.</label> <label>Husb</label>!;
    s!<label>s. Ich</label>!<label>s.</label> <label>Ich</label>!;
    s!<label>s. Jur</label>!<label>s.</label> <label>Jur</label>!;
    s!<label>s. Lap</label>!<label>s.</label> <label>Lap</label>!;
    s!<label>s. Leath</label>!<label>s.</label> <label>Leath</label>!;
    s!<label>s. Ling</label>!<label>s.</label> <label>Ling</label>!;
    s!<label>s. Lt</label>!<label>s.</label> <label>Lt</label>!;
    s!<label>s. Mec.E</label>!<label>s.</label> <label>Mec.E</label>!;
    s!<label>s. Metall</label>!<label>s.</label> <label>Metall</label>!;
    s!<label>s. Mil</label>!<label>s.</label> <label>Mil</label>!;
    s!<label>s. Myth</label>!<label>s.</label> <label>Myth</label>!;
    s!<label>s. Nau</label>!<label>s.</label> <label>Nau</label>!;
    s!<label>s. Orn</label>!<label>s.</label> <label>Orn</label>!;
    s!<label>s. Orn:</label>!<label>s.</label> <label>Orn:</label>!;
    s!<label>s. P</label>!<label>s.</label> <label>P</label>!;
    s!<label>s. Poet</label>!<label>s.</label> <label>Poet</label>!;
    s!<label>s. Rail</label>!<label>s.</label> <label>Rail</label>!;
    s!<label>s. Row</label>!<label>s.</label> <label>Row</label>!;
    s!<label>s. Sculp</label>!<label>s.</label> <label>Sculp</label>!;
    s!<label>s. Ten</label>!<label>s.</label> <label>Ten</label>!;
    s!<label>s. Tex</label>!<label>s.</label> <label>Tex</label>!;
    s!<label>s. Th</label>!<label>s.</label> <label>Th</label>!;
    s!<label>s. Veh</label>!<label>s.</label> <label>Veh</label>!;
    s!<label>s. Ven</label>!<label>s.</label> <label>Ven</label>!;
    s!<label>s. W.Tel</label>!<label>s.</label> <label>W.Tel</label>!;
    s!<label>s. Wr</label>!<label>s.</label> <label>Wr</label>!;
    s!<label>s.pl. Harn</label>!<label>s.pl.</label> <label>Harn</label>!;
    s!<label>v.tr. Cu</label>!<label>v.tr.</label> <label>Cu</label>!;
    s!<label>v.tr. F</label>!<label>v.tr.</label> <label>F</label>!;
    s!<label>v.tr. Lit</label>!<label>v.tr.</label> <label>Lit</label>!;
    s!<label>v.tr. Mec.E</label>!<label>v.tr.</label> <label>Mec.E</label>!;
    s!<label>v.tr. P</label>!<label>v.tr.</label> <label>P</label>!;
    s!<label>v.i. Cr</label>!<label>v.i.</label> <label>Cr</label>!;
    s!<label>v.i. F</label>!<label>v.i.</label> <label>F</label>!;
    s!<label>v.i. P</label>!<label>v.i.</label> <label>P</label>!;
    s!<label>v.i. Sp</label>!<label>v.i.</label> <label>Sp</label>!;
    s!<label>v.i. Th</label>!<label>v.i.</label> <label>Th</label>!;
    print;
}
