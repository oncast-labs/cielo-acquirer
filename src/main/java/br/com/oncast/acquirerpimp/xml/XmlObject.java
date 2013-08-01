package br.com.oncast.acquirerpimp.xml;

public class XmlObject {

	private final Object object;

	public XmlObject(final Object object) {
		this.object = object;
	}

	@SuppressWarnings("unchecked")
	public <T> T as(final Class<T> type) {
		if (is(type)) return (T) object;
		throw new RuntimeException("Could not convert the given object of type " + object.getClass().getSimpleName() + " to requested type " + type.getSimpleName());
	}

	public <T> boolean is(final Class<T> type) {
		return type.isAssignableFrom(object.getClass());
	}

}
