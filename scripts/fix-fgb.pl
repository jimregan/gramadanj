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

    s!<r>\(Pet name and call name for a\) goose. </r>!<r>(Pet name and call name for a)</r> <trans><r>goose. </r></trans>!;
    s!<r> \(Pet name for\) lamb.</r>!<r> (Pet name for)</r> <trans><r>lamb.</r></trans>!;
    s!<r> \(Pet name and call name for\) pup, dog.</r>!<r> (Pet name and call name for)</r> <trans><r>pup, dog.</r></trans>!;
    s!<r> \(Pet name for\) hen. </r>!<r> (Pet name for)</r> <trans><r>hen. </r></trans>!;
    s!<r> \(Call-, pet-, name for\) calf.</r>!<r> (Call-, pet-, name for)</r> <trans><r>calf.</r></trans>!;
    s!<r>\(Call-name for\) cow.</r>!<r>(Call-name for)</r> <trans><r>cow.</r></trans>!;

    s!<r> \(House-\)cricket.</r>!<trans><r> (House-)cricket.</r></trans>!;
    s!<r> \(Nine\)pin. </r>!<trans><r> (Nine)pin. </r></trans>!;
    s!<r>\(Loud\) talk; disputation. </r>!<trans><r>(Loud) talk; disputation. </r></trans>!;
    s!<r> \(Shapeless\) bundle.</r>!<trans><r> (Shapeless) bundle.</r></trans>!;
    s!<r> \(Unnatural\) quarrelling.</r>!<trans><r> (Unnatural) quarrelling.</r></trans>!;
    s!<r> \(High\) ambition.</r>!<trans><r> (High) ambition.</r></trans>!;
    s!<r> \(Chief\) sovereignty.</r>!<trans><r> (Chief) sovereignty.</r></trans>!;
    s!<r> \(High\) esteem. </r>!<trans><r> (High) esteem. </r></trans>!;
    s!<r> \(Glow of\) raked embers.</r>!<trans><r> (Glow of) raked embers.</r></trans>!;
    s!<r>\(Land-\) steward.</r>!<trans><r>(Land-) steward.</r></trans>!;
    s!<r> \(Remnants of\) last year's turf. </r>!<trans><r> (Remnants of) last year's turf. </r></trans>!;
    s!<r>\(Stalk of\) water-lily. </r>!<trans><r>(Stalk of) water-lily. </r></trans>!;
    s!<r>\(Newly-\)baptized person. </r>!<trans><r>(Newly-)baptized person. </r></trans>!;
    s!<r> \(Small\) bank (of earth).</r>!<trans><r> (Small) bank (of earth).</r></trans>!;
    s!<r>\(Small\) bank. </r>!<trans><r>(Small) bank. </r></trans>!;
    s!<r> \(Suction\) pump.</r>!<trans><r> (Suction) pump.</r></trans>!;
    s!<r> \(Shepherd's\) crook. </r>!<trans><r> (Shepherd's) crook. </r></trans>!;
    s!<r> \(Snub\) nose. </r>!<trans><r> (Snub) nose. </r></trans>!;
    s!<r>\(Grooved valve of\) scallop-shell. </r>!<trans><r>(Grooved valve of) scallop-shell. </r></trans>!;
    s!<r> \(Point of\) abutment. </r>!<trans><r> (Point of) abutment. </r></trans>!;
    s!<r>\(Ancient\) stone fort. </r>!<trans><r>(Ancient) stone fort. </r></trans>!;
    s!<r> \(Dried\) cow-dung. </r>!<trans><r> (Dried) cow-dung. </r></trans>!;
    s!<r>\(Bald\) head.</r>!<trans><r>(Bald) head.</r></trans>!;
    s!<r> \(Hare's\) couch.</r>!<trans><r> (Hare's) couch.</r></trans>!;
    s!<r> \(First\) strip in ploughing; central strip of potato-ridge.</r>!<trans><r> (First) strip in ploughing; central strip of potato-ridge.</r></trans>!;
    s!<r>\(Hard\) palate. </r>!<trans><r>(Hard) palate. </r></trans>!;
    s!<r>\(Shower of\) hail. </r>!<trans><r>(Shower of) hail. </r></trans>!;
    s!<r>\(Spanish, sweet\) chestnut. </r>!<trans><r>(Spanish, sweet) chestnut. </r></trans>!;
    s!<r> \(Troops in\) battle order.</r>!<trans><r> (Troops in) battle order.</r></trans>!;
    s!<r>\(Small\) heap, mound. </r>!<trans><r>(Small) heap, mound. </r></trans>!;
    s!<r> \(Ancient\) churchyard. </r>!<trans><r> (Ancient) churchyard. </r></trans>!;
    s!<r> \(Stray\) blade of straw; </r>!<trans><r> (Stray) blade of straw; </r></trans>!;
    s!<r>\(Scarlet\) cloak. </r>!<trans><r>(Scarlet) cloak. </r></trans>!;
    s!<r> \(Astronomical\) cycle.</r>!<trans><r> (Astronomical) cycle.</r></trans>!;
    s!<r>\(Wool-\) comber; fuller. </r>!<trans><r>(Wool-) comber; fuller. </r></trans>!;
    s!<r> \(Wooden\) mug. </r>!<trans><r> (Wooden) mug. </r></trans>!;
    s!<r>\(Wicker\) basket. </r>!<trans><r>(Wicker) basket. </r></trans>!;
    s!<r> \(Field-\)fencing; low embankment. </r>!<trans><r> (Field-)fencing; low embankment. </r></trans>!;
    s!<r>\(Baby's\) napkin.</r>!<trans><r>(Baby's) napkin.</r></trans>!;
    s!<r> \(Small\) flame.</r>!<trans><r> (Small) flame.</r></trans>!;
    s!<r> \(Short\) rushes.</r>!<trans><r> (Short) rushes.</r></trans>!;
    s!<r>\(Ugly\) protuberance. </r>!<trans><r>(Ugly) protuberance. </r></trans>!;
    s!<r> \(Kind of\) limpet. </r>!<trans><r> (Kind of) limpet. </r></trans>!;
    s!<r> \(Thick\) lip.</r>!<trans><r> (Thick) lip.</r></trans>!;
    s!<r> \(Slight\) rash.</r>!<trans><r> (Slight) rash.</r></trans>!;
    s!<r>\(Small\) milking-place. </r>!<trans><r>(Small) milking-place. </r></trans>!;
    s!<r>\(Large, sacred\) tree. </r>!<trans><r>(Large, sacred) tree. </r></trans>!;
    s!<r> \(Boot\) blacking.</r>!<trans><r> (Boot) blacking.</r></trans>!;
    s!<r> \(Female\) stoat. </r>!<trans><r> (Female) stoat. </r></trans>!;
    s!<r>\(Glass\) float. </r>!<trans><r>(Glass) float. </r></trans>!;
    s!<r> \(Office of\) prime.</r>!<trans><r> (Office of) prime.</r></trans>!;
    s!<r> \(Faculty of\) invention.</r>!<trans><r> (Faculty of) invention.</r></trans>!;
    s!<r> \(Spoken\) hint.</r>!<trans><r> (Spoken) hint.</r></trans>!;
    s!<r>\(Made\) of beams.</r>!<trans><r>(Made) of beams.</r></trans>!;
    s!<r> \(Public\) crier. </r>!<trans><r> (Public) crier. </r></trans>!;
    s!<r> \(Small\) hurdle.</r>!<trans><r> (Small) hurdle.</r></trans>!;
    s!<r> \(Bell-\)ringer.</r>!<trans><r> (Bell-)ringer.</r></trans>!;
    s!<r> \(Small species of\) crab. </r>!<trans><r> (Small species of) crab. </r></trans>!;
    s!<r>\(Sheep\)fold.</r>!<trans><r>(Sheep)fold.</r></trans>!;
    s!<r> \(Outer\) edge, side; ledge. </r>!<trans><r> (Outer) edge, side; ledge. </r></trans>!;
    s!<r> \(Small\) sphere.</r>!<trans><r> (Small) sphere.</r></trans>!;
    s!<r> \(Process of\) condensation. </r>!<trans><r> (Process of) condensation. </r></trans>!;
    s!<r>\(Social\) company. </r>!<trans><r>(Social) company. </r></trans>!;
    s!<r> \(Lichen producing\) kind of purple dye.</r>!<trans><r> (Lichen producing) kind of purple dye.</r></trans>!;
    s!<r>\(Angular\) youth. </r>!<trans><r>(Angular) youth. </r></trans>!;
    s!<r> \(Quality of\) perseverance. </r>!<trans><r> (Quality of) perseverance. </r></trans>!;
    s!<r>\(Bodily\) sense. </r>!<trans><r>(Bodily) sense. </r></trans>!;
    s!<r>\(Corn-\)cockle; tares. </r>!<trans><r>(Corn-)cockle; tares. </r></trans>!;
    s!<r>\(Conical\) hive. </r>!<trans><r>(Conical) hive. </r></trans>!;
    s!<r> \(Standing-\)stone.</r>!<trans><r> (Standing-)stone.</r></trans>!;
    s!<r>\(Wedding-\)feast, banquet. </r>!<trans><r>(Wedding-)feast, banquet. </r></trans>!;
    s!<r> \(Sharp point, prickle, bristle.</r>!<trans><r> (Sharp point, prickle, bristle.</r></trans>!;
    s!<r> \(Little\) dove; pigeon. </r>!<trans><r> (Little) dove; pigeon. </r></trans>!;
    s!<r>\(Corresponding\) name. </r>!<trans><r>(Corresponding) name. </r></trans>!;
    s!<r> \(Corn-\)stalk; stubble.</r>!<trans><r> (Corn-)stalk; stubble.</r></trans>!;
    s!<r>\(Small\) roll, coil. </r>!<trans><r>(Small) roll, coil. </r></trans>!;
    s!<r>\(Angular\) youth. </r>!<trans><r>(Angular) youth. </r></trans>!;
    s!<r>\(Water\) plantain. </r>!<trans><r>(Water) plantain. </r></trans>!;
    s!<r> \(Boot-\) protector.</r>!<trans><r> (Boot-) protector.</r></trans>!;
    s!<r>\(Band of\) infantry.</r>!<trans><r>(Band of) infantry.</r></trans>!;
    s!<r>\(Furze\) chopper; cutter, slasher.</r>!<trans><r>(Furze) chopper; cutter, slasher.</r></trans>!;
    s!<r>\(Little\) claw. </r>!<trans><r>(Little) claw. </r></trans>!;
    s!<r>\(Fond of\) backbiting, gossiping.</r>!<trans><r>(Fond of) backbiting, gossiping.</r></trans>!;
    s!<r> \(Fit of\) lonesomeness; anxiety, sorrow. </r>!<trans><r> (Fit of) lonesomeness; anxiety, sorrow. </r></trans>!;
    s!<r> \(Shop\) counter.</r>!<trans><r> (Shop) counter.</r></trans>!;
    s!<r> \(Beggar's, traveller's\) wallet, scrip.</r>!<trans><r> (Beggar's, traveller's) wallet, scrip.</r></trans>!;
    s!<r> \(Small\) tub. </r>!<trans><r> (Small) tub. </r></trans>!;
    s!<r> \(Wooden\) oratory.</r>!<trans><r> (Wooden) oratory.</r></trans>!;
    s!<r> \(Small\) lump; hunk, chunk; portion, quantity. </r>!<trans><r> (Small) lump; hunk, chunk; portion, quantity. </r></trans>!;
    s!<r> \(Little\) foster-child; young pupil.</r>!<trans><r> (Little) foster-child; young pupil.</r></trans>!;
    s!<r> \(Bardic\) company; party, retinue. </r>!<trans><r> (Bardic) company; party, retinue. </r></trans>!;
    s!<r> \(Small\) bowl; cup.</r>!<trans><r> (Small) bowl; cup.</r></trans>!;
    s!<r>\(Small\) bundle. </r>!<trans><r>(Small) bundle. </r></trans>!;
    s!<r>\(Small\) bundle, ball. </r>!<trans><r>(Small) bundle, ball. </r></trans>!;
    s!<r> \(Small\) cavity. </r>!<trans><r> (Small) cavity. </r></trans>!;
    s!<r>\(Hoar-\)frost; frosty vapour. </r>!<trans><r>(Hoar-)frost; frosty vapour. </r></trans>!;
    s!<r>\(Stone indicating\) base in rounders. </r>!<trans><r>(Stone indicating) base in rounders. </r></trans>!;
    s!<r> \(Little\) oak.</r>!<trans><r> (Little) oak.</r></trans>!;
    s!<r> \(Little\) clod. </r>!<trans><r> (Little) clod. </r></trans>!;
    s!<r> \(Built-up\) heap. </r>!<trans><r> (Built-up) heap. </r></trans>!;
    s!<r> \(Article of\) hardware. </r>!<trans><r> (Article of) hardware. </r></trans>!;
    s!<r> \(Article of\) enamelware. </r>!<trans><r> (Article of) enamelware. </r></trans>!;
    s!<r>\(Small\) claw or hoof. </r>!<trans><r>(Small) claw or hoof. </r></trans>!;
    s!<r>\(Small\) hook or claw. </r>!<trans><r>(Small) hook or claw. </r></trans>!;
    s!<r>\(Little\) claw or hoof. </r>!<trans><r>(Little) claw or hoof. </r></trans>!;
    s!<r>\(Small\) harp. </r>!<trans><r>(Small) harp. </r></trans>!;
    s!<r>\(Of office, post\) Deanery.</r>!<trans><r>(Of office, post) Deanery.</r></trans>!;
    s!<r> \(Small\) hole, cavity.</r>!<trans><r> (Small) hole, cavity.</r></trans>!;
    s!<r> \(Species of\) pollock. </r>!<trans><r> (Species of) pollock. </r></trans>!;
    s!<r> \(Turning-\) lathe. </r>!<trans><r> (Turning-) lathe. </r></trans>!;
    s!<r>\(Pair of\) shears. </r>!<trans><r>(Pair of) shears. </r></trans>!;
    s!<r> \(John\) Dory. </r>!<trans><r> (John) Dory. </r></trans>!;
    s!<r> \(Small\) ear of corn.</r>!<trans><r> (Small) ear of corn.</r></trans>!;
    s!<r> \(State of\) orphanage.</r>!<trans><r> (State of) orphanage.</r></trans>!;
    s!<r> \(Vindicatory\) preface. </r>!<trans><r> (Vindicatory) preface. </r></trans>!;
    s!<r>\(Little\) wisp. </r>!<trans><r>(Little) wisp. </r></trans>!;
    s!<r>\(Illusory\) form, false appearance. </r>!<trans><r>(Illusory) form, false appearance. </r></trans>!;

    s/  */ /g;

    print;
}
