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

my $mtversion = 1;

binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");

while(<>) {
    # Proofreading error
    s#<trg>lománaíocht <label>f</label>#<trg>Iománaíocht <label>f</label>#;
    # Content outside of <label>
    s#</src>, a<label>\.</label>#</src>, <label>a.</label>#g;
    # Specific fixes
    s#<label>Déanaim amas</label>#<trg>Déanaim amas</trg>#;
    s#\(i gcoir</trg>, <trg>etc\.\)#(i gcoir, etc.)#;
    s#\(buille</trg>, <trg>fuaime\)#(buille, fuaime)#;
    s#\(lae</trg>, <trg>etc\.\), #(lae, etc.)</trg>, <trg>#;
    s#\(gallúnaí</trg>, <trg>etc\.\), #(gallúnaí, etc.)</trg>, <trg>#;
    s#\(airgead</trg>, <trg>etc\.\)#(airgead, etc.)#;
    s#\(ar thraein</trg>, <trg>etc\.\), #(ar thraein, etc.)</trg>, <trg>#;
    s#\(drochnóis</trg>, <trg>etc\.\), #(drochnóis, etc.)</trg>, <trg>#;
    s#\(Dé</trg>, <trg>Eaglaise\)</trg>#(Dé, Eaglaise)</trg>#;
    s#<trg>airde <label>f, treise f</label> \(glóir</trg>, <trg>gutha\)</trg>#<trg>airde <label>f</label>, treise <label>f</label> (glóir, gutha)</trg>#;
    s#do chara</trg>, <trg>don choróin#do chara, don choróin#;
    s#\(seilge</trg>, <trg>lucht#(seilge, lucht#;
    s#</trg>, <trg>etc\., de léim\)</trg>#, etc., de léim)</trg>#;
    s#</trg>, <trg>etc\.\)</trg>#, etc.)</trg>#g;
    s#([A-Za-záéíóú]*)</trg>, <trg>([A-Za-záéíóú][a-záéíóú ,]*[a-záéíóú]), etc\.\)#$1, $2, etc.)#g;
    s#([A-Za-záéíóú]*)</trg>, <trg>([A-Za-záéíóú][a-záéíóú ,]*[a-záéíóú])\)#$1, $2)#g;
    s#([A-Za-záéíóú]*)</trg>, <trg>([A-Za-záéíóú][a-záéíóú ]*[a-záéíóú]), etc\.\)#$1, $2, etc.)#g;
    s#([A-Za-záéíóú]*)</trg>, <trg>([A-Za-záéíóú][a-záéíóú ]*[a-záéíóú])\)#$1, $2)#g;
    s#\(([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*)\)#($1, $2)#g;
    s#\(([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*), etc\.\)#($1, $2, etc.)#g;
    s#([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*)\)#$1, $2)#g;
    s#([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*), etc\.\)#$1, $2, etc.)#g;
    s#([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*)\)#$1, $2)#g;
    s#([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*), etc\.\)#$1, $2, etc.)#g;
    s#</trg>, <trg>veidhlín, acraí\)</trg>#, veidhlín, acraí)</trg>#;
    s#</trg>, <trg>sléibhte, eachtraí\)</trg>#sléibhte, eachtraí)</trg>#;
    s#</trg>, <trg>talaimh, bóthair iarainn\)</trg>#, talaimh, bóthair iarainn)</trg>#;
    s#<noindex>\(<label>v\.n\.</label> <trg>-ach</trg>\)</noindex>#<noindex>(<label>v.n.</label> -ach)</noindex>#;
    s#<trg>riocht <label>m</label> \(g</trg>\. <trg>reachta\), #<trg>riocht <label>m</label></trg> <noindex>(<label>g.</label> reachta)</noindex>, <trg>#;
    s#</label> \(cú</trg>, <trg>cait, etc\.\)</trg>#</label> (cú, cait, etc.)</trg>#;
    s#<trg>Crios <label>m</label> \(g</trg>\. <trg>creasa\)</trg>#<trg>Crios <label>m</label> (<label>g.</label> creasa)</trg>#;
    s#<label>Cinceasú</label> <label>m</label>#<trg>Cinceasú <label>m</label></trg>#;
    s#<trg>dian <noindex>\(<label>to, towards</label>, ar\)</noindex></trg>#<trg>dian</trg> <noindex>(<src>to, towards</src>, <trg>ar</trg>)</noindex>#;
    s#\(cuid d'inneall</trg>, <trg>slabhra, etc\.\)#(cuid d'inneall, slabhra, etc.)#;
    s#\(bean f\)#<noindex>(bean <label>f</label>)</noindex>#;
    s#<trg>Nuachtán <label>m</label> páipéar <label>m</label>#<trg>Nuachtán <label>m</label></trg>, <trg>páipéar <label>m</label>#;
    s#<trg>Fionn <label>m, sú-adhmad m</label>#<trg>Fionn <label>m</label>, sú-adhmad <label>m</label>#;
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
    # <label> instead of <trg>
    s#<label>Amas</label> <label>m</label>#<trg>Amas <label>m</label></trg>#;
    s#<label>Amas</label>#<trg>Amas</trg>#;
    s#<label>Arach</label>#<trg>Arach</trg>#;

    # fix combination of <label> and unmarked that ought to have been <src> and <trg>
    s# <noindex>\(<label>([^<]*)</label>, ([^)]*)\)</noindex></trg>#</trg> <noindex>(<src>$1</src>, <trg>$2</trg>)</noindex>#g;
    # similar to the above, seems to be either a conversion error, or one part was missing.
    s#<label>([^<]*)</label> \(<src>([^<]*)</src>, \[([^<]*)</trg>\)#<label>$1</label></trg> <noindex>(<src>$2</src>, <trg>$3</trg>)</noindex>#g;
    # rejoin pieces of disambiguating context that were split (the second part is *not* a translation)
    s#\(([^<]*)</trg>, <trg>([^<]*)</trg>\.\)\.#($1, $2.)</trg>.#g;
    # Normalise spaces
    s!<noindex>\( <label>([^<]*)</label> \)</noindex>!<noindex>(<label>$1</label>)</noindex>!g;
    s/  */ /g;
    # full stop after </src> before <label> (in some cases, it looks right)
    s!</src>\. <label>!</src>, <label>!g;

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
    s!<label>Pr.n. Geog</label>!<label>Pr.n.</label> <label>Geog</label>!;
    s!<label>Pr.n. Ph</label>!<label>Pr.n.</label> <label>Ph</label>!;
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
    s!<label>pl. F</label>!<label>pl.</label> <label>F</label>!;
    s!<label>pl. Nau</label>!<label>pl.</label> <label>Nau</label>!;
    s!<label>int. F</label>!<label>int.</label> <label>F</label>!;
    s!Carp: <label>Feirim</label>!<label>Carp:</label> <trg>Feirim</trg>!;
    s!Mch: <label>Mír chinn</label>!<label>Mch:</label> <trg>Mír chinn</trg>!;

    s!<label>Q.V.</label> UNDER BACK3 1, 2.!Q.V. UNDER BACK<super>3</super> 1, 2.!;

    # Duplicate; not working earlier?
    s#</trg>, <trg>etc\.\)</trg>#, etc.)</trg>#g;
    s#([a-záéíóú]*)</trg>, <trg>([a-záéíóú]*), etc\.\)#$1, $2, etc.)#g;
    s!\(ealaíne</trg>\., <trg>etc</trg>\.\)!(ealaíne, etc.)</trg>!;
    s!\(gréine</trg>, <trg>gealaí</trg>; <trg>tí solais\)</trg>!(gréine, gealaí; tí solais)</trg>!;
    # Duplicated ')' probable cause of missing <noindex>
    s! \(<src>with</src>, <trg>le\)</trg>\)\.! <noindex>(<src>with</src>, <trg>le</trg>)</noindex>.!;
    s!feola\), cróicéad m</trg>!feola)</trg>, <trg>cróicéad <label>m</label></trg>!;
    s!<trg>Sórt <label>m</label>, saghas!<trg>Sórt <label>m</label></trg>, <trg>saghas!;
    if($mtversion) {
        s!<trg>cuirim fearas <label>m</label></trg>, <trg>trealamh <label>m</label> \(i muileann, siopa, etc\.\)</trg>!<trg>cuirim fearas, trealamh (i muileann, siopa, etc.)</trg>!;
    } else {
        s!<trg>cuirim fearas <label>m</label></trg>, <trg>trealamh <label>m</label> \(i muileann, siopa, etc\.\)</trg>!<trg>cuirim fearas <label>m</label>, trealamh <label>m</label> (i muileann, siopa, etc.)</trg>!;
    }
    s!pholaitíocht</trg>, <trg>sa saol!pholaitíocht, sa saol!;
    s!\(achta</trg>, <trg>ainm ar!(achta, ainm ar!;
    s!\(crúiscín</trg>, cupáin\)\]; colpán <label>m</label> \(súiste\); crann <label>m</label> \(speile\); glac <label>f</label>, lonna <label>m</label> \(maide rámha\)!(crúiscín, cupáin)</trg>; <trg>colpán <label>m</label> (súiste)</trg>; <trg>crann <label>m</label> (speile)</trg>; <trg>glac <label>f</label></trg>, <trg>lonna <label>m</label> (maide rámha)</trg>!;
    s!bhFian</trg>, <trg>catha!bhFian, catha!;
    s!Foghlaí <label>m</label>, treaspásóir!Foghlaí <label>m</label></trg>, <trg>treaspásóir!;
    s!albam</trg>, <trg>páipéar!albam, páipéar!;
    s!\(bliana</trg>, <trg>oibre,!(bliana, oibre,!;
    s!\(costais</trg>, <trg>páirteanna!(costais, páirteanna!;
    s!\(ealaíne</trg>\., <trg>etc\.\)</trg>.!(ealaíne, etc.)</trg>.!;
    s!\(cáipéise\), gan glacadh!(cáipéise)</trg>, <trg>gan glacadh!;
    s!\(diúic</trg>, <trg>etc</trg>\.\),!(diúic, etc.)</trg>,!;
    s!\(leighis</trg>, <trg>etc</trg>\.\),!(leighis, etc.)</trg>,!;
    s#\(bríceadóireachta</trg>, <trg>etc\.\)</trg>#(bríceadóireachta, etc.)</trg>#;
    s#\(de théad</trg>, <trg>d'éadach\)</trg>#(de théad, d'éadach)</trg>#;
    s#\(bus</trg>, <trg>etc\.\)#(bus, etc.)#;
    s#\(miúl</trg>, <trg>etc\.\)#(miúl, etc.)#;
    s#\(gruaige</trg>, <trg>etc\.\)</trg>#(gruaige, etc.)</trg>#;
    s#\(abhann</trg>, <trg>etc\.\)</trg>#(abhann, etc.)</trg>#;
    s#\(airgid</trg>, <trg>etc\.\) ag obair</trg>#(airgid, etc.) ag obair</trg>#;
    s#\(<label>gm</label> -imh</trg>; <trg><label>gf</label> -lún\)</trg>#<noindex>(<label>gm</label> -imh); <label>gf</label> -lún)</noindex>#;
    s#\(scoile</trg>, <trg>etc\.\)#(scoile, etc.)#;
    s#\(litreach</trg>, <trg>etc\.\), faillí#(litreach, etc.)</trg>, <trg>faillí#;
    s#\(oifigeach</trg>, <trg>etc\.\)#(oifigeach, etc.)#;
    s#\(earraí</trg>; <trg>báid#(earraí; báid#;
    s#\(comhla</trg>; <trg>imthaca#(comhla; imthaca#;
    s#\(boinn</trg>, <trg>etc\.\)#(boinn, etc.)#;
    s#<trg><label>s.o. from doing sth</label>\.,dhuine rud a dhéanamh\)</trg># <noindex>(<src>s.o. from doing sth.</src>, <trg>dhuine rud a dhéanamh</trg>)</noindex>#;
    s#</label> \(of sth</trg>\., <trg>ruda, ar rud\)</trg>#</label></trg> <noindex>(<src>of sth.</src>, <trg>ruda, ar rud</trg>)</noindex>#;
    s#\(ruacain</trg>, <trg>etc\.\)#(ruacain, etc.)#;
    s#\(liáin</trg>, <trg>etc.\)#(liáin, etc.)#;
    s#\(guail</trg>, <trg>etc\.\), scaob#(guail, etc.)</trg>, <trg>scaob#;
    s#\(searrach <label>m</label></trg>, <trg>uan <label>m</label></trg>, <trg>etc\.\)#(searrach <label>m</label>, uan <label>m</label>, etc.)#;
    s# \(s.o. from doing sth</trg>\]\., <trg>duine ó rud a dhéanamh\)</trg>#</trg> <noindex>(<src>s.o. from doing sth.</src>, <trg>duine ó rud a dhéanamh</trg>)</noindex>#;
    s!</trg>, <trg>etc\.\) droma</trg>!, etc.) droma</trg>!;
    s#<trg>Déanaim argóint \(against sth\., in aghaidh ruda\)</trg>#<trg>Déanaim argóint</trg> <noindex>(<src>against sth.</src>, <trg>in aghaidh ruda</trg>)</noindex>#;
    s#<trg>Caithim</trg>, <trg>déanaim</trg>, <trg>fleá</trg>#<trg>Caithim, déanaim, fleá</trg>#;
    s#<trg>Cromaim anuas</trg>, <trg>síos</trg>#<trg>Cromaim anuas, síos</trg>#;
    s#<trg>Lúbaim síos</trg>, <trg>anuas</trg>#<trg>Lúbaim síos, anuas</trg>#;
    s!<trg>taisme m cinniúint <label>f</label>!<trg>taisme <label>m</label></trg> <trg>cinniúint <label>f</label>!;
    s!<trg>Cur <label>m</label> chun bóthair</trg>, <trg>chun siúil, imeacht!<trg>Cur <label>m</label> chun bóthair, chun siúil</trg>, <trg>imeacht!;
    s!<trg>bronnadh m, dáil!<trg>bronnadh <label>m</label></trg>, <trg>dáil!;
    s!<trg>Reithe <label>m</label> \[cogaidh</trg>\]\.!<trg>Reithe <label>m</label> cogaidh</trg>.!;
    s!ósta&gt;!ósta!;
    s!<trg>Chump-chop, gríscín <label>m</label> puint</trg>!<src>Chump-chop</src>, <trg>gríscín <label>m</label> puint</trg>!;
    s!<trg>plate camera, ceamara <label>m</label> pláta</trg>!<src>plate camera</src>, <trg>ceamara <label>m</label> pláta</trg>!;
    s!<trg>Hemlock fir, giúis <label>f</label> himlice</trg>!<src>Hemlock fir</src>, <trg>giúis <label>f</label> himlice</trg>!;
    s!<trg>Cock-bird, éan <label>m</label> coiligh</trg>!<src>Cock-bird</src>, <trg>éan <label>m</label> coiligh</trg>!;
    s!<trg>Lus m na gcnámh briste, meacan <label>!<trg>Lus <label>m</label> na gcnámh briste</trg>, <trg>meacan <label>!;
    s!<trg>Teilgtheoir <label>m</label>, oibrí <label>m</label> teilgcheárta</trg>!<trg>Teilgtheoir <label>m</label></trg>, <trg>oibrí <label>m</label> teilgcheárta</trg>!;
    s!<trg>Contact(-piece), giota <label>m</label> tadhaill</trg>!<src>Contact(-piece)</src>, <trg>giota <label>m</label> tadhaill</trg>!;
    s!<trg>Cúrsa <label>m</label> léinn, curaclam m</trg>!<trg>Cúrsa <label>m</label> léinn</trg>, <trg>curaclam <label>m</label></trg>!;
    s!<trg>Stamp-pad, ceap <label>m</label> stampa</trg>!<src>Stamp-pad</src>, <trg>ceap <label>m</label> stampa</trg>!;
    s!<trg>Turn of a sentence, leagan <label>m</label> abairte</trg>!<src>Turn of a sentence<src>, <trg>leagan <label>m</label> abairte</trg>!;
    s!<trg>Calabrú m, tástáil <label>!<trg>Calabrú <label>m</label></trg>, <trg>tástáil <label>!;
    s!<trg>Clo <label>m</label> dubh</trg>!<trg>Cló <label>m</label> dubh</trg>!;
    s!<trg>Duct <label>m</label> aeir (éisc, etc.)</trg>!<trg>Ducht <label>m</label> aeir (éisc, etc.)</trg>!;

    # S.a. pieces
    s!<line xml:space="preserve">S.a. RUG1. </line>!<line xml:space="preserve">S.a. RUG 1. </line>!;
    s!<line xml:space="preserve">S.a. SEA1. </line>!<line xml:space="preserve">S.a. SEA 1. </line>!;
    s!<line xml:space="preserve">S.a. BEATEN1. </line>!<line xml:space="preserve">S.a. BEATEN 1. </line>!;
    s!<line xml:space="preserve">S.a. FORTUNE1. </line>!<line xml:space="preserve">S.a. FORTUNE 1. </line>!;
    s!<line xml:space="preserve">S.a. CALENDAR1. </line>!<line xml:space="preserve">S.a. CALENDAR 1. </line>!;
    s!<line xml:space="preserve">S.a. RUBBISH 2, SCANDAL2. </line>!<line xml:space="preserve">S.a. RUBBISH 2, SCANDAL 2. </line>!;
    s!<line xml:space="preserve">S.a. FINE3\^ 6. </line>!<line xml:space="preserve">S.a. FINE<super>3</super> 6. </line>!;
    s!<line xml:space="preserve">S.a. WIFE1. </line>!<line xml:space="preserve">S.a. WIFE 1. </line>!;
    s!<line xml:space="preserve">S.a. ALONE 2, FLY<super>3</super> I\. 4, HAVE<super>2</super> 3, LOOSE<super>1</super> I\. </line>!<line xml:space="preserve">S.a. ALONE 2, FLY<super>3</super> I, 4, HAVE<super>2</super> 3, LOOSE<super>1</super> I. </line>!;
    s!<line xml:space="preserve">S.a. LAST2, I. 1 NEXT I. 1, 2. </line>!<line xml:space="preserve">S.a. LAST<super>2</super>, I. 1 NEXT I. 1, 2. </line>!;
    s!<line xml:space="preserve">S.a. FLASH2,3 1. </line>!<line xml:space="preserve">S.a. FLASH<super>2</super>,<super>3</super> 1. </line>!;
    s!<line xml:space="preserve">S.a. BURN<super>2</super> 1, PICK3 7. </line>!<line xml:space="preserve">S.a. BURN<super>2</super> 1, PICK<super>3</super> 7. </line>!;
    s!<line xml:space="preserve">S.a. COMPANY1  2.  </line>!<line xml:space="preserve">S.a. COMPANY<super>1</super> 2.</line>!;

    # want to keep the ')' with the disambiguating context with which it belongs, to not include that information as a translation
    s#etc</trg>\.\)\.#etc.)</trg>.#g;
    s#etc</trg>\.\),#etc.)</trg>,#g;
    s#etc</trg>\.\);#etc.)</trg>;#g;
    s!<trg>Cúl <label>m</label> din</trg>!<trg>Cúl <label>m</label> dín</trg>!;

    print;
}
