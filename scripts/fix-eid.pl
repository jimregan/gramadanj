#!/usr/bin/perl

use warnings;
use strict;

while(<>) {
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#;
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    s#<label>([mf]) -([^<]*)</label></trg>#<label>$1</label> -$2</trg>#;

    s#etc</trg>.\).#etc.)</trg>.#;
    s# <noindex>\(<label>([^<]*)</label>, ([^)]*)\)</noindex></trg>#</trg> <noindex>(<src>$1</src>, <trg>$2</trg>)</noindex>#;
    s#<label>([^<]*)</label> \(<src>([^<]*)</src>, \[([^<]*)</trg>\)#<label>$1</label></trg> <noindex>(<src>$2</src>, <trg>$3</trg>)</noindex>#;
    s#\(([^<]*)</trg>, <trg>([^<]*)</trg>\.\)\.#($1, $2.)</trg>.#g;
    print;
}
