package utils;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean isNotBlank(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	public static String encryptPhone(String phone) {
		return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
	}

	public static String encryptRealName(String realName) {
		String x = "";
		for (int i = 0; i < realName.substring(1).length(); i++) {
			x += "*";
		}
		return realName.substring(0, 1) + x;
	}

}
