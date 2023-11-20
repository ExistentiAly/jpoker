package ca.sariarra.util;

public class ParamUtil {

	public static void notNull(String pName, Object param) {
		if (param == null) {
			throw new IllegalArgumentException(pName + " must not be null.");
		}
	}
	
	public static void notNullOrEmpty(String pName, Object[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException(pName + " must not be null.");
		}
		if (arr.length == 0) {
			throw new IllegalArgumentException(pName + " must not be empty.");
		}
	}
}
