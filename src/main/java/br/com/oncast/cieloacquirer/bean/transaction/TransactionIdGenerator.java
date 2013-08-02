package br.com.oncast.cieloacquirer.bean.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionIdGenerator {

	private static final SimpleDateFormat TO_SEQUENCIAL_NUMBER_FORMAT = new SimpleDateFormat("yyyyMMddhhmm");

	private static final int MAX_ID_DIGITS = 8;

	private static final int ID_VALUE_LIMIT = (int) Math.pow(10, MAX_ID_DIGITS);

	private static final String LEADING_ZEROS_FORMAT = "%0" + MAX_ID_DIGITS + "d";

	private static int counter = 0;

	public static String generate() {
		return getTimestamp() + String.format(LEADING_ZEROS_FORMAT, ++counter % ID_VALUE_LIMIT);
	}

	private static String getTimestamp() {
		return TO_SEQUENCIAL_NUMBER_FORMAT.format(new Date());
	}

}
