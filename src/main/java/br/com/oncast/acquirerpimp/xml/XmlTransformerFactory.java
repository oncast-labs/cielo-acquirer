package br.com.oncast.acquirerpimp.xml;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

public class XmlTransformerFactory {

	private Set<Class<?>> supportedTypes;

	private boolean formattedOutput;

	private String encoding;

	private XmlTransformerFactory() {
		supportedTypes = getTypesSupportedByDefault();
		formattedOutput = false;
		encoding = "UTF-8";
	}

	public static XmlTransformerFactory get() {
		return new XmlTransformerFactory();
	}

	public XmlTransformerFactory setSupportedTypes(Set<Class<?>> supportedTypes) {
		this.supportedTypes = supportedTypes;
		return this;
	}

	public XmlTransformerFactory addSupportedTypes(Class<?>... supportedTypes) {
		for (Class<?> type : supportedTypes) {
			this.supportedTypes.add(type);
		}
		return this;
	}

	public XmlTransformerFactory addSupportedTypes(Collection<Class<?>> supportedTypes) {
		this.supportedTypes.addAll(supportedTypes);
		return this;
	}

	public XmlTransformerFactory setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public XmlTransformerFactory setFormattedOutput(boolean formattedOutput) {
		this.formattedOutput = formattedOutput;
		return this;
	}

	public XmlTransformer build() {
		return new XmlTransformer(supportedTypes, formattedOutput, encoding);
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