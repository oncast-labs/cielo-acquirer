package br.com.oncast.cieloacquirer.bean.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.oncast.cieloacquirer.utils.NumberStringUtils;

public class TransactionIdGenerator {

	private static final SimpleDateFormat TO_SEQUENCIAL_NUMBER_FORMAT = new SimpleDateFormat("yyyyMMddhhmm");

	private static final int MAX_ID_DIGITS = 8;

	private static int counter = 0;

	public static String generate() {
		return getTimestamp() + NumberStringUtils.formatWithLeadingZeros(++counter, MAX_ID_DIGITS);
	}

	private static String getTimestamp() {
		return TO_SEQUENCIAL_NUMBER_FORMAT.format(new Date());
	}

}
