#!/usr/bin/perl

use warnings;
use strict;
use utf8;

while(<>) {
	chomp;
	my $copy = $_;
	$copy =~ s/\^//g;
	my $mult = 0;
	my @words = split /\$/, $mult;

	my @pieces = ();
	for my $word (@words) {
		@pieces = split/\//;
		if($#pieces != 1) {
			$mult = 1;
			next;
		}
	}
#	if($mult == 1) {
#		$mult = 0;
#		next;
#	}
#	if(/^\^([^\/]*)\/([^<]*)([^\$]*)\$
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Weak><Pl><DefArt>\$;?$/) {
		print "Nm_na_NmGenW\t$1 na $3\t$2,na,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Strong><Pl><DefArt>\$;?$/) {
		print "Nm_na_NmGenS\t$1 na $3\t$2,na,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Gen><Sg><Def><Fem>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>/) {
		print "Nm_na_Nf\t$1 na $3\t$2,na,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^na\/na<Art><Gen><Sg><Def><Fem>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Gen><Len>\$;?$/) {
		print "NPm_na_NPf\t$1 na $3\t$2,na,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Dat><Sg><Ecl>\$;?$/) {
		print "Nm_Pr_NfDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Nm_Pr_NfComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Nm_Pr_NfComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Dat><Sg><Ecl>\$;?$/) {
		print "Nm_Pr_NmDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Nm_Pr_NmComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Nm_Pr_NmComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Dat><Sg><Ecl>\$;?$/) {
		print "Nf_Pr_NfDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Nf_Pr_NfComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Nf_Pr_NfComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Dat><Sg><Ecl>\$;?$/) {
		print "Nf_Pr_NmDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Nf_Pr_NmComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Nf_Pr_NmComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Pl><Ecl>\$;?$/) {
		print "Nm_Pr_NmPlComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Dat><Sg><Ecl>\$;?$/) {
		print "Nm_PrA_NfDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Nm_PrA_NfComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Nm_PrA_NfComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Dat><Sg><Ecl>\$;?$/) {
		print "Nm_PrA_NmDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Nm_PrA_NmComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Nm_PrA_NmComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Dat><Sg><Ecl>\$;?$/) {
		print "Nf_PrA_NfDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Nf_PrA_NfComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Nf_PrA_NfComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Dat><Sg><Ecl>\$;?$/) {
		print "Nf_PrA_NmDatE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Nf_PrA_NmComE\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Nf_PrA_NmComL\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><DefArt>\$;?$/) {
		print "Nm_PrA_NfComDef\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><DefArt>\$;?$/) {
		print "Nm_PrA_NmComDef\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><DefArt>\$;?$/) {
		print "Nf_PrA_NfComDef\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^<]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><DefArt>\$;?$/) {
		print "Nf_PrA_NmComDef\t$1 $3 $5\t$2,$4,$6\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$;?$/) {
		print "An_NPf\tAn $1\tan,$2\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "An_NPfDefArt\tAn $1\tan,$2\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg><DefArt>\$;?$/) {
		print "An_NPfDefArt\tAn $1\tan,$2\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$;?$/) {
		print "An_NPm\tAn $1\tan,$2\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\$;?$/) {
		print "Nm_Nf\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\$;?$/) {
		print "Nf_Nf\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg><Len>\$;?$/) {
		print "Nf_NfL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\$;?$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\$;?$/) {
		print "Nf_Nm\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Strong><Pl>\$;?$/) {
		print "Nf_NmGenS\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Weak><Pl>\$;?$/) {
		print "Nf_NmGenW\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Strong><Pl>\$;?$/) {
		print "Nm_NmGenS\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Weak><Pl>\$;?$/) {
		print "Nm_NmGenW\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Strong><Pl>\$;?$/) {
		print "Nf_NfGenS\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Weak><Pl>\$;?$/) {
		print "Nf_NfGenW\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Strong><Pl>\$;?$/) {
		print "Nm_NfGenS\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Weak><Pl>\$;?$/) {
		print "Nm_NfGenW\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Subst><Noun><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\$;?$/) {
		print "Su_Nf\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Subst><Noun><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\$;?$/) {
		print "Su_Nm\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Com><Pl>\$;?$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Com><Pl>\$;?$/) {
		print "Nf_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\/([^<]*)<Noun><Fem><Gen><Sg><Ecl>\$;?$/) {
		print "Nm_Nf\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nf\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Gen><Sg><Ecl>\$;?$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Fem><Com><Sg><Len>\$;?$/) {
		print "Nf_A\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "Nf_AB\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Masc><Com><Sg>\$;?$/) {
		print "Nm_A\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base>\$;?$/) {
		print "Nm_AB\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\$;?$/) {
		print "Nm_VA\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\$;?$/) {
		print "Nf_VA\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><NStem>\$;?$/) {
		print "Nm_NSt\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><NStem>\$;?$/) {
		print "Nf_NSt\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)>\$;?$/) {
		print "Nf_VN$5\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)>\$;?$/) {
		print "Nm_VN$5\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)><Gen>\$;?$/) {
		print "Nf_VN$5Gen\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)><Gen>\$;?$/) {
		print "Nm_VN$5Gen\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)><Gen><Len>\$;?$/) {
		print "Nf_VN$5GenL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)><Gen><Len>\$;?$/) {
		print "Nm_VN$5GenL\t$1 $3\t$2,$4\n";
	}
  # Only applies to two entries, so assuming output gender is fine
	if(/^\^([^\/]*)\/([^<]*)<Verbal><Noun><V(TI|T|I)>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Pl>\$;?$/) {
		print "Nm:VN$3NmComPl\t$1 $4\t$2,$5\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Foreign><English><Noun>\$;?$/) {
		print "Nm_NEn\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Foreign><English><Noun>\$;?$/) {
		print "Nf_NEn\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\/([^<]*)<Adj><Base>\$;?$/) {
		print "Nm_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "Nf_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\/([^<]*)<Verbal><Adj>\$;?$/) {
		print "Nm_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\/([^<]*)<Verbal><Adj><Len>\$;?$/) {
		print "Nf_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$\^an\/an<Art><Sg><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg><DefArt>\$;?$/) {
		print "Adv:Pr_NmE_an_Nm\t$1 $3 an $5\t$2,$4,an,$6\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Fem><Com><Sg><Len>\$;?$/) {
		print "NPf_A\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "NPf_AB\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Masc><Com><Sg>\$;?$/) {
		print "NPm_A\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base>\$;?$/) {
		print "NPm_AB\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\$;?$/) {
		print "NPm_VA\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\$;?$/) {
		print "NPf_VA\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\/([^<]*)<Adj><Base>\$;?$/) {
		print "NPm_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "PNm_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "NPf_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "PNf_VA\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/^\^([^\/]*)\/([^<]*)<Adv><Its>\$\^([^\/]*)\/([^<]*)<Adj><Base>\$;?$/) {
		print "Adv:Adv_Adj\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Adv:Pr_NmE\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Adv:Pr_NfE\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Adv:Pr_NmL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Adv:Pr_NfL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$;?$/) {
		print "Adv:Pr_Nm\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$;?$/) {
		print "Adv:Pr_Nf\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;?$/) {
		print "Adv:PrA_NmE\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;?$/) {
		print "Adv:PrA_NfE\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;?$/) {
		print "Adv:PrA_NmL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;?$/) {
		print "Adv:PrA_NfL\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$;?$/) {
		print "Adv:PrA_Nm\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Prep><Art><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$;?$/) {
		print "Adv:PrA_Nf\t$1 $3\t$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Fem><Com><Sg><Len>\$;?$/) {
		print "An_NPf_A\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "An_NPf_AB\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Masc><Com><Sg>\$;?$/) {
		print "An_NPm_A\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg><DefArt>\$\^([^\/]*)\/([^<]*)<Adj><Masc><Com><Sg>\$;?$/) {
		print "An_NPmDef_A\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg><Len>\$\^([^\/]*)\/([^<]*)<Adj><Fem><Com><Sg><Len>\$;?$/) {
		print "An_NPfDef_A\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base>\$;?$/) {
		print "An_NPm_AB\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\$;?$/) {
		print "An_NPm_VA\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\$;?$/) {
		print "An_NPf_VA\tAn $1 $3\tan,$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Prep><Simp>\$;?$/) {
		print "Nm_Pr\t$1 $3\t$2,$4\n";
	}
	if(/^\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Prep><Simp>\$;?$/) {
		print "Nf_Pr\t$1 $3\t$2,$4\n";
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\/([^<]*)<Adj><Base>\$;?$/) {
		print "An_NPm_VA\tAn $1 $3\tan,$2,$4\n";
		if($5 ne $4) {
			print STDERR "An_Nf_Nm\tAn $1 $3\tan,$2,$4::$_\n";
		}
	}
	if(/^\^An\/\?<\?>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\/([^<]*)<Adj><Base><Len>\$;?$/) {
		print "An_NPf_VA\tAn $1 $3\tan,$2,$4\n";
		if($5 ne $4) {
			print STDERR "An_Nf_Nm\tAn $1 $3\tan,$2,$4::$_\n";
		}
	}
#	if(/^\^([^\/]*)\/([^<]*)([^\$]*)\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)([^\$]*)\$;?$/) {
#		print "$1 $2 $3 na $4 $5 $6\n";
#	}
}
