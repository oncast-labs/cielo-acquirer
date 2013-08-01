package br.com.oncast.acquirerpimp.bean.establishment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "dados-ec")
@XmlAccessorType(XmlAccessType.NONE)
public class Establishment {

	@XmlElement(name = "numero")
	private long id;

	@XmlElement(name = "chave")
	private String key;

	Establishment() {}

	public Establishment(long id, String key) {
		this.id = id;
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Establishment other = (Establishment) obj;
		if (id != other.id) return false;
		if (key == null) {
			if (other.key != null) return false;
		} else if (!key.equals(other.key)) return false;
		return true;
	}

}
