#!/usr/bin/perl

use warnings;
use strict;

while(<>) {
    # Content outside of <label>
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#;
    # Specific fixes
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    s#<label>Cinceasú</label> <label>m</label>#<trg>Cinceasú <label>m</label></trg>#;
    s#<trg>dian <noindex>\(<label>to, towards</label>, ar\)</noindex></trg>#<trg>dian</trg> <noindex>(<src>to, towards</src>, <trg>ar</trg>)</noindex>#;
    # Genitive with gender
    s#<label>([mf]) -([^<]*)</label></trg>#<label>$1</label> -$2</trg>#;
    # a second noun and its gender attached to the first noun's gender
    s#<label>([mf]), ([^ ]*) ([mf])</label></trg>#<label>$1</label></trg>, <trg>$2 <label>$3</label></trg>#;
    # Conversion error: put into <trg> instead of <label>
    s#<label>([^ ]*) &lt;([mf])</label> ([^&]*)&gt;#<trg>$1 <label>$2</label> $3</trg>#;

    # want to keep the ')' with the disambiguating context with which it belongs, to not include that information as a translation
    s#etc</trg>.\).#etc.)</trg>.#;
    # fix combination of <label> and unmarked that ought to have been <srg> and <trg>
    s# <noindex>\(<label>([^<]*)</label>, ([^)]*)\)</noindex></trg>#</trg> <noindex>(<src>$1</src>, <trg>$2</trg>)</noindex>#;
    # similar to the above, seems to be either a conversion error, or one part was missing.
    s#<label>([^<]*)</label> \(<src>([^<]*)</src>, \[([^<]*)</trg>\)#<label>$1</label></trg> <noindex>(<src>$2</src>, <trg>$3</trg>)</noindex>#;
    # rejoin pieces of disambiguating context that were split (the second part is *not* a translation)
    s#\(([^<]*)</trg>, <trg>([^<]*)</trg>\.\)\.#($1, $2.)</trg>.#g;
    print;
}
