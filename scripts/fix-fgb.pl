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
use utf8;

binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");

my $mtversion = 1;

while(<>) {
    s!<n>1\. S\.a\. </n><s>!<n>1. </n><a>S.a. </a><s>!;
    s!<g>npl\. ~</g><b>a, </b>!<g>npl. </g><b>~a, </b>!;
    s!<g>npl\. ~</g><b>a</b>!<g>npl. </g><b>~a</b>!;
    s!<g>gpl\. ~</g>\)\.!<g>gpl. </g><b>~</b>).!;
    s!<b>( *[0-9]+\. *)</b>!<n>$1</n>!g;

    s!<trans><r>See </r></trans><s>([^<]*)</s>!<a>See </a><s>$1</s>!g;

    s!<g>pred\. a\. Lit</g>!<g>pred. a. </g><c>Lit</c>!;
    s!<g>pred\. a\. used with copula\. Lit</g>!<g>pred. a. used with copula. </g><c>Lit</c>!;
    s!<g>pred\.a\. Lit</g>!<g>pred.a. </g><c>Lit</c>!;
    s!<g>pref\. Lit</g>!<g>pref. </g><c>Lit</c>!;
    s!<g>s\. El</g>!<g>s. </g><c>El</c>!;
    s!<g>s\. Jur</g>!<g>s. </g><c>Jur</c>!;
    s!<g>s\. Lit</g>!<g>s. </g><c>Lit</c>!;
    s!<g>s\. conj\. &amp; adv\. Lit</g>!<g>s. conj. &amp; adv. </g><c>Lit</c>!;
    s!<g>spl\. Artil</g>!<g>spl. </g><c>Artil</c>!;
    s!<g>spl\. Com</g>!<g>spl. </g><c>Com</c>!;
    s!<g>spl\. F</g>!<g>spl. </g><c>F</c>!;
    s!<g>spl\. Geol</g>!<g>spl. </g><c>Geol</c>!;
    s!<g>spl\. Jur</g>!<g>spl. </g><c>Jur</c>!;
    s!<g>spl\. Lit</g>!<g>spl. </g><c>Lit</c>!;
    s!<g>spl\. Mapm</g>!<g>spl. </g><c>Mapm</c>!;
    s!<g>spl\. Mec</g>!<g>spl. </g><c>Mec</c>!;
    s!<g>spl\. Myth</g>!<g>spl. </g><c>Myth</c>!;
    s!<g>spl\. Nau</g>!<g>spl. </g><c>Nau</c>!;
    s!<g>spl\. Th</g>!<g>spl. </g><c>Th</c>!;
    s!<g>suff\. Biol</g>!<g>suff. </g><c>Biol</c>!;
    s!<g>suff\. Ch</g>!<g>suff. </g><c>Ch</c>!;
    s!<g>v\.i\. Astr</g>!<g>v.i. </g><c>Astr</c>!;
    s!<g>v\.i\. Ch</g>!<g>v.i. </g><c>Ch</c>!;
    s!<g>v\.i\. El</g>!<g>v.i. </g><c>El</c>!;
    s!<g>v\.i\. Jur</g>!<g>v.i. </g><c>Jur</c>!;
    s!<g>v\.i\. Lit</g>!<g>v.i. </g><c>Lit</c>!;
    s!<g>v\.i\. Mec\.E</g>!<g>v.i. </g><c>Mec.E</c>!;
    s!<g>v\.i\. Phil</g>!<g>v.i. </g><c>Phil</c>!;
    s!<g>a1\. Log</g>!<g>a1. </g><c>Log</c>!;
    s!<g>v\.t\. &amp; i\. Biol</g>!<g>v.t. &amp; i. </g><c>Biol</c>!;
    s!<g>v\.t\. &amp; i\. Lit</g>!<g>v.t. &amp; i. </g><c>Lit</c>!;
    s!<g>v\.t\. &amp; i\. Metall</g>!<g>v.t. &amp; i. </g><c>Metall</c>!;
    s!<g>v\.t\. &amp; i\. Ph</g>!<g>v.t. &amp; i. </g><c>Ph</c>!;
    s!<g>v\.t\. &amp; i\. W\. Tel</g>!<g>v.t. &amp; i. </g><c>W.Tel</c>!;
    s!<g>v\.t\. Adm</g>!<g>v.t. </g><c>Adm</c>!;
    s!<g>v\.t\. Ar</g>!<g>v.t. </g><c>Ar</c>!;
    s!<g>v\.t\. Ch</g>!<g>v.t. </g><c>Ch</c>!;
    s!<g>v\.t\. Com</g>!<g>v.t. </g><c>Com</c>!;
    s!<g>v\.t\. Ecc</g>!<g>v.t. </g><c>Ecc</c>!;
    s!<g>v\.t\. El</g>!<g>v.t. </g><c>El</c>!;
    s!<g>v\.t\. Electronics</g>!<g>v.t. </g><c>Electronics</c>!;
    s!<g>v\.t\. Gram</g>!<g>v.t. </g><c>Gram</c>!;
    s!<g>v\.t\. Hort</g>!<g>v.t. </g><c>Hort</c>!;
    s!<g>v\.t\. Journ</g>!<g>v.t. </g><c>Journ</c>!;
    s!<g>v\.t\. Jur</g>!<g>v.t. </g><c>Jur</c>!;
    s!<g>v\.t\. Ling</g>!<g>v.t. </g><c>Ling</c>!;
    s!<g>v\.t\. Lit</g>!<g>v.t. </g><c>Lit</c>!;
    s!<g>v\.t\. Log</g>!<g>v.t. </g><c>Log</c>!;
    s!<g>v\.t\. Mec\.E</g>!<g>v.t. </g><c>Mec.E</c>!;
    s!<g>v\.t\. Metalw</g>!<g>v.t. </g><c>Metalw</c>!;
    s!<g>v\.t\. Mil</g>!<g>v.t. </g><c>Mil</c>!;
    s!<g>v\.t\. Mth</g>!<g>v.t. </g><c>Mth</c>!;
    s!<g>v\.t\. Mus</g>!<g>v.t. </g><c>Mus</c>!;
    s!<g>v\.t\. Ph</g>!<g>v.t. </g><c>Ph</c>!;
    s!<g>v\.t\. Phil</g>!<g>v.t. </g><c>Phil</c>!;
    s!<g>v\.t\. Psy</g>!<g>v.t. </g><c>Psy</c>!;
    s!<g>v\.t\. Th</g>!<g>v.t. </g><c>Th</c>!;
    s!<g>v\.t\. W\. Tel</g>!<g>v.t. </g><c>W.Tel</c>!;
    s!<g>v\.t\. W\.Tel</g>!<g>v.t. </g><c>W.Tel</c>!;
    # Partly included multiword in my version (removed because it was
    # counted as an example). As I don't have access to the full version, I
    # can't do anything here
    if($mtversion) {
	s!<h>ment\)\. </h>!!;
    }
    s!<title>aillt</title>\(<h>r\)eacha, </h>!<title>aillt(r)eacha, </title>!;
    s!<g>Lit</g>!<c>Lit</c>!;
    s!<a>S\.a\. \~smadra1 4</a>!<a>S.a. </a><s>madra</s><x>1</x> <n>4</n>!;
    s!<c>b</c>!<l>b</l>!;
    s!<c>e</c>!<l>e</l>!;
    s!<c>Conj\. </c>!<g>Conj. </g>!;
    s!<c>Dim\. of </c>!<g>Dim. of </g>!;
    s!<c>Pr\.n</c>!<g>Pr.n</g>!;
    s!<c>i</c>!<l>i</l>!;
    s!<c>lit</c>!<g>lit</g>!;
    s!</trans>\*\x7FbIn \~ linbh, <trans>!</trans><i>In ~ linbh</i><p>, </p><trans>!;
    s!<trans><r> picking bones, (ii) </r></trans>!<trans><r> picking bones</r></trans>, <r>(ii) </r>!;
    s!<r> Old, worn-out, horseshowe.</r>!<r>Old, worn-out, horseshoe.</r>!;
    s!</i>\)<p>!)</i><p>!;
    s!<l>II 1\(a</l>\)\.<p>!<r>II</r> <n>1</n>(<l>a</l>).<p>!;
    s!<l>I 1\(a</l>\)!<r>I</r> <n>1</n>(<l>a</l>)!;

    s!</n><r>\(Act of\) ([^<]*)</r>!</n><r>(Act of)</r> <trans><r>$1</r></trans>!;
    s!</b><r>\(Act of\) ([^<]*)</r>!</b><r>(Act of)</r> <trans><r>$1</r></trans>!;

    s/  */ /g;

    print;
}
