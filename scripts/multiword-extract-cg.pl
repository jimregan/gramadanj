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
#	if(/\^([^\/]*)\/([^<]*)([^\$]*)\$
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Weak><Pl><DefArt>\$;$/) {
		print "Nm_na_NmGenW\t$1 na $3\t$2,na,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Strong><Pl><DefArt>\$;$/) {
		print "Nm_na_NmGenS\t$1 na $3\t$2,na,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^na\/na<Art><Gen><Sg><Def><Fem>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>/) {
		print "Nm_na_Nf\t$1 na $3\t$2,na,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prop><Noun><Masc><Com><Sg>\$\^na\/na<Art><Gen><Sg><Def><Fem>\$\^([^\/]*)\/([^<]*)<Prop><Noun><Fem><Gen><Len>\$;$/) {
		print "NPm_na_NPf\t$1 na $3\t$2,na,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\$;$/) {
		print "Nm_Nf\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\$;$/) {
		print "Nf_Nf\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\$;$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\$;$/) {
		print "Nf_Nm\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Com><Pl>\$;$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Com><Pl>\$;$/) {
		print "Nf_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Gen><Sg>\/([^<]*)<Noun><Fem><Gen><Sg><Ecl>\$;$/) {
		print "Nm_Nf\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nf\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg>\/([^<]*)<Noun><Masc><Gen><Sg><Ecl>\$;$/) {
		print "Nm_Nm\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nm_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Fem><Com><Sg><Len>\$;$/) {
		print "Nf_A\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base><Len>\$;$/) {
		print "Nf_AB\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Masc><Com><Sg>\$;$/) {
		print "Nm_A\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Adj><Base>\$;$/) {
		print "Nm_AB\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\$;$/) {
		print "Nm_VA\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\$;$/) {
		print "Nf_VA\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj>\/([^<]*)<Adj><Base>\$;$/) {
		print "Nm_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$\^([^\/]*)\/([^<]*)<Verbal><Adj><Len>\/([^<]*)<Adj><Base><Len>\$;$/) {
		print "Nf_VA\t$1 $3\t$2,$4\n";
		if($5 ne $4) {
			print STDERR "Nf_Nm\t$1 $3\t$2,$4::$_\n";
		}
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$\^an\/an<Art><Sg><Def>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Gen><Sg><DefArt>\$;$/) {
		print "Adv:Pr_NmE_an_Nm\t$1 $3 an $5\t$2,$4,an,$6\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Ecl>\$;$/) {
		print "Adv:Pr_NmE\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Ecl>\$;$/) {
		print "Adv:Pr_NfE\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg><Len>\$;$/) {
		print "Adv:Pr_NmL\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg><Len>\$;$/) {
		print "Adv:Pr_NfL\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Masc><Com><Sg>\$;$/) {
		print "Adv:Pr_Nm\t$1 $3\t$2,$4\n";
	}
	if(/\^([^\/]*)\/([^<]*)<Prep><Simp>\$\^([^\/]*)\/([^<]*)<Noun><Fem><Com><Sg>\$;$/) {
		print "Adv:Pr_Nf\t$1 $3\t$2,$4\n";
	}
#	if(/\^([^\/]*)\/([^<]*)([^\$]*)\$\^na\/na<Art><Pl><Def>\$\^([^\/]*)\/([^<]*)([^\$]*)\$;$/) {
#		print "$1 $2 $3 na $4 $5 $6\n";
#	}
}
