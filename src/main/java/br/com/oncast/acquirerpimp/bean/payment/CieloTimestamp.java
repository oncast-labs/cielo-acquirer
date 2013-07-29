package br.com.oncast.acquirerpimp.bean.payment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "data-hora")
@XmlAccessorType(XmlAccessType.NONE)
public class CieloTimestamp implements Serializable {

	private static final SimpleDateFormat CIELO_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

	private static final long serialVersionUID = 1L;

	@XmlValue
	private String cieloFormattedDate = null;

	public CieloTimestamp() {
		cieloFormattedDate = toCieloDateTimeFormat(new Date());
	}

	private String toCieloDateTimeFormat(Date date) {
		return CIELO_DATE_TIME_FORMAT.format(date);
	}

	public Date getDate() {
		try {
			return CIELO_DATE_TIME_FORMAT.parse(cieloFormattedDate);
		} catch (ParseException e) {
			throw new RuntimeException("Could not parse cielo date to java Date", e);
		}
	}

}
