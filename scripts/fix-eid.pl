#!/usr/bin/perl

use warnings;
use strict;

while(<>) {
    # Content outside of <label>
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#;
    # This one is specific
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    # Genitive with gender
    s#<label>([mf]) -([^<]*)</label></trg>#<label>$1</label> -$2</trg>#;

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
