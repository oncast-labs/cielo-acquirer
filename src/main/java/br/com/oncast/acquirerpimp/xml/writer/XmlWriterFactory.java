package br.com.oncast.acquirerpimp.xml.writer;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

public class XmlWriterFactory {

	private Set<Class<?>> supportedTypes;

	private boolean formattedOutput;

	private String encoding;

	private XmlWriterFactory() {
		supportedTypes = getTypesSupportedByDefault();
		formattedOutput = false;
		encoding = "UTF-8";
	}

	public static XmlWriterFactory get() {
		return new XmlWriterFactory();
	}

	public XmlWriterFactory setSupportedTypes(Set<Class<?>> supportedTypes) {
		this.supportedTypes = supportedTypes;
		return this;
	}

	public XmlWriterFactory addSupportedTypes(Class<?>... supportedTypes) {
		for (Class<?> type : supportedTypes) {
			this.supportedTypes.add(type);
		}
		return this;
	}

	public XmlWriterFactory addSupportedTypes(Collection<Class<?>> supportedTypes) {
		this.supportedTypes.addAll(supportedTypes);
		return this;
	}

	public XmlWriterFactory setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public XmlWriterFactory setFormattedOutput(boolean formattedOutput) {
		this.formattedOutput = formattedOutput;
		return this;
	}

	public XmlWriter build() {
		return new XmlWriter(supportedTypes, formattedOutput, encoding);
	}

	private Set<Class<?>> getTypesSupportedByDefault() {
		Set<Class<? extends Serializable>> subTypesOf = new Reflections("br.com.oncast.acquirerpimp.bean").getSubTypesOf(Serializable.class);
		HashSet<Class<?>> notInterfacesTypes = new HashSet<Class<?>>();
		for (Class<?> class1 : subTypesOf) {
			if (!class1.isInterface()) notInterfacesTypes.add(class1);
		}
		return notInterfacesTypes;
	}

}