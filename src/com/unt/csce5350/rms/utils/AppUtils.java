package com.unt.csce5350.rms.utils;


import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AppUtils {

	
	public static boolean isEmpty(String str) {
		if(str!=null && str.trim().length()>0) {
			return false;
		}
		return true;
	}
	
	
	public static <T> boolean isEmpty( List<T> list) {
		if(list!=null && !list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean containsSpecialCharacter(String s) {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		boolean b = m.find();
		return b;
	}
	

	
	
	public static long getDateDiffInMinutes(Date d1, Date d2) {
		if(d1==null || d2==null) {
			return 0;
		}
		
		long diffInMilliSecs = d2.getTime() - d1.getTime();
		long diffInMinutes   = diffInMilliSecs / (60 * 1000) % 60; 
		
		return diffInMinutes;
		

	}
	
	
	public static boolean compareStrings(String s1, String s2) {
		if(s1!=null && s1!=null && s1.equals(s2)) {
			return true;
		}
		return false;
	}

}
