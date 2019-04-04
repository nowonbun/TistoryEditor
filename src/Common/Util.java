package Common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import Common.IF.LambdaExpression;

public class Util {

	private final static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy/MM/dd");
	private final static DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static DateFormat javascriptDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static boolean StringEquals(String val1, String val2) {
		if (val1 == null) {
			return false;
		}
		if (val2 == null) {
			return false;
		}
		return val1.equals(val2);
	}

	public static boolean StringEqualsUpper(String val1, String val2) {
		if (val1 == null) {
			return false;
		}
		if (val2 == null) {
			return false;
		}
		return val1.toUpperCase().equals(val2.toUpperCase());
	}

	public static boolean StringIsEmptyOrNull(String val) {
		if (val == null) {
			return true;
		}
		if (val.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static Date getDateFromString(String pDate) {
		try {
			return yyyyMMddFormat.parse(pDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static String createCookieKey() {
		String key = UUID.randomUUID().toString();
		return key.replace("-", "") + dateFormat2.format(new Date());
	}

	public static int getCookieExpire() {
		return 60 * 60 * 24 * PropertyMap.getInstance().getPropertyInt("config", "cookie_expire");
	}

	public static String getCookiePath() {
		return PropertyMap.getInstance().getProperty("config", "cookie_path");
	}

	public static <T> T searchArray(T[] array, LambdaExpression<T, Boolean> condition) {
		if (array == null) {
			return null;
		}
		for (T node : array) {
			if (condition.run(node)) {
				return node;
			}
		}
		return null;
	}

	public static String convertDateFormat(Date date) {
		return javascriptDateFormat.format(date);
	}
}
