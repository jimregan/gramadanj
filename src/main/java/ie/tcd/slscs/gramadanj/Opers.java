package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form.Mutation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Opers {
	public static String Demutate (String text) {
		String ret=text;
		ret=Utils.s(ret, "^[bB][hH]([fF].*)$", "$1");
		ret=Utils.s(ret, "^([bcdfgmpstBCDFGMPST])[hH](.*)$", "$1$2");
		ret=Utils.s(ret, "^[mM]([bB].*)$", "$1");
		ret=Utils.s(ret, "^[gG]([cC].*)$", "$1");
		ret=Utils.s(ret, "^[nN]([dD].*)$", "$1");
		ret=Utils.s(ret, "^[nN]([gG].*)$", "$1");
		ret=Utils.s(ret, "^[bB]([pP].*)$", "$1");
		ret=Utils.s(ret, "^[tT]([sS].*)$", "$1");
		ret=Utils.s(ret, "^[dD]([tT].*)$", "$1");
		ret=Utils.s(ret, "^[dD]'([fF])[hH](.*)$", "$1$2");
		ret=Utils.s(ret, "^[dD]'([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
		ret=Utils.s(ret, "^[hH]([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
		ret=Utils.s(ret, "^[nN]-([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
		
		return ret;
	}
	
	public static String Mutate (Mutation mutation, String text) {
		String ret = text;
		if ((mutation == Mutation.Len1) || (mutation == Mutation.Len1D)
			|| (mutation == Mutation.Len2) || (mutation == Mutation.Len2D)				
			|| (mutation == Mutation.Len3) || (mutation == Mutation.Len3D)) {
			if (text.matches("^([pbmftdcgPBMFTDCG])[jJ]")) {
				return text;
			}
			if ((mutation == Mutation.Len1) || (mutation == Mutation.Len1D)) {
				ret=Utils.s(ret, "^([pbmftdcgPBMFTDCG])(.*)$", "$1h$2");
				ret=Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "$1h$2");
			} else {
				ret=Utils.s(ret, "^([pbmfcgPBMFCG])(.*)$", "$1h$2");
				if ((mutation == Mutation.Len3) || (mutation == Mutation.Len3D)) {
					ret = Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "t$1$2");
				}
			}
			if ((mutation == Mutation.Len1D) || (mutation == Mutation.Len2D) 
				|| (mutation == Mutation.Len3D)) {
				ret = Utils.s(ret, "^([aeiouáéíóúAEIOUÁÉÍÓÚfF])(.*)$", "d'$1$2");
				//ret = s(ret, "^([aeiouáéíóúAEIOUÁÉÍÓÚ])(.*)$", "d'$1$2");
				//ret = s(ret, "^([fF])(.*)$", "d'fh$2");
			}
		}
		if ((mutation == Mutation.Ecl1) || (mutation == Mutation.Ecl1x)
			|| (mutation == Mutation.Ecl2) || (mutation == Mutation.Ecl3)) {
			ret=Utils.s(ret, "^([pP])(.*)$", "b$1$2");
			ret=Utils.s(ret, "^([bB])(.*)$", "m$1$2");
			ret=Utils.s(ret, "^([fF])(.*)$", "bh$1$2");
			ret=Utils.s(ret, "^([cC])(.*)$", "g$1$2");
			ret=Utils.s(ret, "^([gG])(.*)$", "n$1$2");
			if ((mutation == Mutation.Ecl1) || (mutation == Mutation.Ecl1x)) {
				ret=Utils.s(ret, "^([tT])(.*)$", "d$1$2");
				ret=Utils.s(ret, "^([dD])(.*)$", "n$1$2");
			}
			if (mutation == Mutation.Ecl1) {
				ret=Utils.s(ret, "^([aeiuoáéíúó])(.*)$", "n-$1$2");
				ret=Utils.s(ret, "^([AEIUOÁÉÍÚÓ])(.*)$", "n$1$2");
			}
			if (mutation == Mutation.Ecl3) {
				ret=Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "t$1$2");
			}
		}
		if (mutation == Mutation.PrefT) {
			ret=Utils.s(ret, "^([aeiuoáéíúó])(.*)$", "t-$1$2");
			ret=Utils.s(ret, "^([AEIUOÁÉÍÚÓ])(.*)$", "t$1$2");
		}
		if (mutation == Mutation.PrefH) {
			ret=Utils.s(ret, "^([aeiuoáéíúóAEIUOÁÉÍÚÓ])(.*)$", "h$1$2");
		}
		return ret;
	}
	
	public static String Consonants = "bcdfghjklmnpqrstvwxz";
	public static String Vowels = "aeiouáéíóú";
	public static String VowelsBroad = "aouáóú";
	public static String VowelsSlender = "eiéí";

    /**
     * Regular broadening: changes a slender vowel/vowel cluster to its
     * broad equivalent, if the input string ends with a consonant
     * cluster; if not, the string is returned unchanged.
     * See {@link #Slenderise(String)}, which performs the opposite operation.
     * @param bayse input
     * @return broadened string
     */
	public static String Broaden(String bayse) {
		String ret = bayse;
		String[] sources = new String[] {"ói", "ei", "éi", "i",  "aí",  "í",  "ui", "io"};
		String[] targets = new String[] {"ó",  "ea", "éa", "ea", "aío", "ío", "o",  "ea"};
		Matcher m;
		for (int i=0; i < sources.length; i++) {
			Pattern p1 = Pattern.compile("^(.*[" + Consonants + "])?" + sources[i] + "([" + Consonants + "]+)$");
			m = p1.matcher(bayse);
			if (m.matches()) {
				ret = m.group(1) + targets[i] + m.group(2);
				return ret;
			}
			Pattern p2 = Pattern.compile("^(.*)i([" + Consonants + "]+)$");
			m = p2.matcher(bayse);
			if (m.matches()) {
				ret = m.group(1) + m.group(2);
			}
		}
		return ret;
	}

    /**
     * Performs slenderisation of a word, replacing a broad vowel cluster
     * with its slender equivalent, if the input ends with a consonant
     * cluster; if not, the string is returned unchanged.
     * See {@link #Broaden(String)}, which performs the opposite operation.
     * @param bayse
     * @return
     */
    public static String Slenderise(String bayse) {
        String ret = bayse;
        String[] sources = new String[] {"ea", "éa", "ia", "ío", "io", "iu", "ae"};
        String[] targets = new String[] {"i",  "éi", "éi", "í",  "i",  "i",  "aei"};
        Matcher m;
        for (int i=0; i < sources.length; i++) {
            Pattern p1 = Pattern.compile("^(.*[" + Consonants + "])?" + sources[i] + "([" + Consonants + "]+)$");
            m = p1.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + targets[i] + m.group(2);
                return ret;
            }
            Pattern p2 = Pattern.compile("^(.*[" + VowelsBroad + "])([" + Consonants + "]+)$");
            m = p2.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + "i" + m.group(2);
            }
        }
        return ret;
    }

    /**
     * Spelling variation of {@link #Slenderise(String)}, to match original
     * @param bayse
     * @return
     */
    public static String Slenderize(String bayse) {
        return Slenderise(bayse);
    }

	/**
	 * Devoices word final 'sd' to 'st'
	 * @param bayse Input string
	 * @return Devoiced string
	 */
	public static String Devoice(String bayse) {
		String ret = bayse;
		ret = Utils.s(ret, "^(.*)sd$", "$1st");
		return ret;
	}
	
	/**
	 * Checks if a string ends with a vowel
	 * @param bayse String to match
	 * @return true if bayse ends with a vowel, false otherwise
	 */
	public static boolean EndsVowel(String bayse) {
		return bayse.matches("[aeiouáéíóúAEIOUÁÉÍÓÚ]$");
	}
	
	/**
	 * Checks if a string starts with a vowel
	 * @param bayse String to match
	 * @return true if bayse starts with a vowel, false otherwise
	 */
	public static boolean StartsVowel(String bayse) {
		return bayse.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]");
	}

	/**
	 * Checks if word ends with "DeNTalS" consonant
	 * @param bayse String to match
	 * @return true if strings ends with d, n, t, or s;
	 * false otherwise
     */
	public static boolean EndsDentals(String bayse) {
		return bayse.matches("[dntsDNTS]$");
	}
}
