package th.ac.kmutt.research.utils;

public class TokenUtils {
	public static String genToken(int number){
		StringBuffer sb = new StringBuffer();
	    for (int i = number; i > 0; i -= 12) {
	      int n = Math.min(12, Math.abs(i));
	      sb.append(org.apache.commons.lang3.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
	    }
	    return sb.toString();
}
}
