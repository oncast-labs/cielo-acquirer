package br.com.oncast.cieloacquirer.xml;

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

	public XmlTransformerFactory setSupportedTypes(final Set<Class<?>> supportedTypes) {
		this.supportedTypes = supportedTypes;
		return this;
	}

	public XmlTransformerFactory addSupportedTypes(final Class<?>... supportedTypes) {
		for (final Class<?> type : supportedTypes) {
			this.supportedTypes.add(type);
		}
		return this;
	}

	public XmlTransformerFactory addSupportedTypes(final Collection<Class<?>> supportedTypes) {
		this.supportedTypes.addAll(supportedTypes);
		return this;
	}

	public XmlTransformerFactory setEncoding(final String encoding) {
		this.encoding = encoding;
		return this;
	}

	public XmlTransformerFactory setFormattedOutput(final boolean formattedOutput) {
		this.formattedOutput = formattedOutput;
		return this;
	}

	public XmlTransformer build() {
		return new XmlTransformer(supportedTypes, formattedOutput, encoding);
	}

	private Set<Class<?>> getTypesSupportedByDefault() {
		final Set<Class<? extends Serializable>> subTypesOf = new Reflections("br.com.oncast.cieloacquirer.bean").getSubTypesOf(Serializable.class);
		final HashSet<Class<?>> notInterfacesTypes = new HashSet<Class<?>>();
		for (final Class<?> class1 : subTypesOf) {
			if (!class1.isInterface()) notInterfacesTypes.add(class1);
		}
		return notInterfacesTypes;
	}

}