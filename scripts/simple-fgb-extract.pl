#!/usr/bin/perl

use warnings;
use strict;
use utf8;

binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");


sub demerkin {
    my $in = shift;
    $in =~ s/izer$/iser/;
    $in =~ s/ization$/isation/;
    $in =~ s/ize$/ise/;
    $in;
}

my %posmap = (
    "m" => "m",
    "f" => "f",
    "s" => "n",
    "a1" => "adj",
    "a2" => "adj",
    "a3" => "adj",
    "a" => "adj",
    "adv" => "adv",
    "v.i" => "vblex",
    "v.t" => "vblex",
    "v.t. &amp; i" => "vblex",
    "prep" => "pr",
    "pref" => "PREFIX",
    "int" => "ij",
    "pron" => "prn",
    "gs. as attrib.a" => "ATTRIB",
    "gs. as a" => "ATTRIB",
    "num. s. &amp; a" => "num",
    "spl" => "n",
    "mpl" => "n",
    "fpl" => "n",
    "conj" => "cnj",
    "suff. f" => "SUFFIX",
    "suff. m" => "SUFFIX",
);

while(<>) {
    chomp;

    s!<i>[^<]*</i><b>[^<]*</b><trans><r>, </r></trans>!!;

    my $title = '';
    if(/<title>([^<]*)<\/title>/) {
        $title = trim($1);
    }
    my $pos = '';
    my $posraw = '';
    if(/<g>([^<(]*)[\(<]/) {
        $posraw = $1;
        if(exists $posmap{trim($posraw)}) {
            $pos = $posmap{trim($posraw)};
        } else {
print STDERR "$posraw\n";
        }
    }
    my $res = '';
    if(!$pos || $pos eq '') {
        $pos = 'FIXME';
        $res = ' i="yes"';
    }
    if ($pos eq 'ATTRIB' || $pos eq 'PREFIX' || $pos eq 'SUFFIX') {
        $res = ' i="yes"';
    }
    my $posleft = "<s n=\"$pos\"/>";
    my $posright = "<s n=\"$pos\"/>";
    if($pos eq 'm' || $pos eq 'f') {
        $posright = "<s n=\"n\"/>";
        $posleft = "<s n=\"n\"/><s n=\"$pos\"/>";
    }
    while($_ =~ /<trans><r>([^<]*)<\/r><\/trans>/g) {
        my $trans = trim($1);
        for my $tr (split/[,;!]/, $trans) {
            $tr = trim($tr);
            $tr = demerkin($tr);
            if ($title eq lc($title)) {
                $tr = lc($tr);
            }
            if ($tr =~ /the letter/i) {
                $res = ' i="yes"';
            }
            print "    <e$res><p><l>$tr$posright</l><r>$title$posleft</r></p></e>\n";
        }
    }
}

sub trim {
    my $in = shift;
    $in =~ s/^ *//;
    $in =~ s/ *$//;
    $in =~ s/,$//;
    $in =~ s/\.$//;
    $in =~ s/;$//;
    $in =~ s/!$//;
    $in;
}


