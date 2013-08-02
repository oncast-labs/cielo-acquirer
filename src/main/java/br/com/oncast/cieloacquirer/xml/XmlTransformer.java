package br.com.oncast.cieloacquirer.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

public class XmlTransformer {

	private Marshaller marshaller = null;

	private Unmarshaller unmarshaller = null;

	private final Set<Class<?>> supportedTypes;

	private boolean formattedOutput;

	private final String encoding;

	private JAXBContext context;

	public XmlTransformer(final Set<Class<?>> supportedTypes, final boolean formattedOutput, final String encoding) {
		this.supportedTypes = supportedTypes;
		this.formattedOutput = formattedOutput;
		this.encoding = encoding;
	}

	public String toXml(final Object obj) {
		try {
			final StringWriter sw = new StringWriter();
			getMarshaller().marshal(obj, sw);
			return sw.toString();
		} catch (final JAXBException e) {
			throw new RuntimeException("Could not convert to XML", e);
		}
	}

	public XmlObject fromXml(final String xml) {
		try {
			return new XmlObject(getUnmarshaller().unmarshal(new StringReader(xml)));
		} catch (final JAXBException e) {
			throw new RuntimeException("Could parse the XML", e);
		}
	}

	public String removeNamespace(final String xml) {
		return xml.replaceAll(" xmlns=\".+\"", "");
	}

	public XmlTransformer setFormattedOutput(final boolean formattedOutput) throws PropertyException {
		this.formattedOutput = formattedOutput;
		if (marshaller != null) marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		return this;
	}

	private Marshaller getMarshaller() throws JAXBException, PropertyException {
		if (marshaller == null) {
			marshaller = getContext().createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		}
		return marshaller;
	}

	private Unmarshaller getUnmarshaller() throws JAXBException, PropertyException {
		if (unmarshaller == null) {
			unmarshaller = getContext().createUnmarshaller();
		}
		return unmarshaller;
	}

	private JAXBContext getContext() throws JAXBException {
		if (context == null) context = JAXBContext.newInstance(supportedTypes.toArray(new Class[supportedTypes.size()]));
		return context;
	}

	public static void print(final Object object) {
		System.out.println(XmlTransformerFactory.get().setFormattedOutput(true).build().toXml(object));
	}

}
