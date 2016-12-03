#!/usr/bin/perl

use warnings;
use strict;

while(<>) {
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#;
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    s#<label>m -a</label></trg>#<label>m</label> -a</trg>#;

    s# <noindex>\(<label>([^<]*)</label>, ([^)]*)\)</noindex></trg>#</trg> <noindex>(<src>$1</src>, <trg>$2</trg>)</noindex>#;
    s#\(([^<]*)</trg>, <trg>([^<]*)</trg>\.\)\.#($1, $2.)</trg>.#g;
    print;
}
