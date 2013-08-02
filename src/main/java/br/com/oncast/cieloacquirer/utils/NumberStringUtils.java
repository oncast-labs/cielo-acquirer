package br.com.oncast.cieloacquirer.utils;

public class NumberStringUtils {

	/**
	 * Examples:
	 * <ul>
	 * <li>formatWithLeadingZeros(1, 3) ==> "001"
	 * <li>formatWithLeadingZeros(123, 3) ==> "123"
	 * <li>formatWithLeadingZeros(1001, 3) ==> "001"
	 * </ul>
	 * 
	 * @param number
	 *            the number that will be formatted
	 * @param numberOfDigits
	 *            the number of digits in the string, it is also the string's length
	 * @return the formatted string like the example.
	 */
	public static String formatWithLeadingZeros(final long number, final int numberOfDigits) {
		return String.format("%0" + numberOfDigits + "d", number % (int) Math.pow(10, numberOfDigits));
	}
}
