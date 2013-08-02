package br.com.oncast.cieloacquirer.bean.payment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "data-hora")
@XmlAccessorType(XmlAccessType.NONE)
public class CieloTimestamp implements Serializable {

	private static final SimpleDateFormat CIELO_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

	private static final long serialVersionUID = 1L;

	@XmlValue
	private String cieloFormattedDate = null;

	public CieloTimestamp() {
		cieloFormattedDate = toCieloDateTimeFormat(new Date());
	}

	private String toCieloDateTimeFormat(final Date date) {
		return CIELO_DATE_TIME_FORMAT.format(date);
	}

	public Date getDate() {
		try {
			return CIELO_DATE_TIME_FORMAT.parse(cieloFormattedDate);
		} catch (final ParseException e) {
			throw new RuntimeException("Could not parse cielo date to java Date", e);
		}
	}

	@Override
	public String toString() {
		return cieloFormattedDate;
	}
}
