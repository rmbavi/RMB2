package com.example.rmb.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String string) {
		if (string == null || "".equals(string)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(String string) {
		if (string != null && !"".equals(string)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param desc
	 *            Ŀ���ַ���
	 * @param length
	 *            ��ȡ�ĳ���
	 */
	public static String getSubString(String desc, int length) {
		if (desc.length() < length) {
			return desc;
		} else {
			return desc.substring(0, length) + "...";
		}

	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
