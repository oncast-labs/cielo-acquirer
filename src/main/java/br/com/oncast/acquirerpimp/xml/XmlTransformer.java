package br.com.oncast.acquirerpimp.xml;

import java.io.OutputStream;
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

	public XmlTransformer(Set<Class<?>> supportedTypes, boolean formattedOutput, String encoding) {
		this.supportedTypes = supportedTypes;
		this.formattedOutput = formattedOutput;
		this.encoding = encoding;
	}

	public void write(Object obj, OutputStream os) throws JAXBException {
		getMarshaller().marshal(obj, os);
	}

	public String toXmlString(Object obj) throws JAXBException {
		StringWriter sw = new StringWriter();
		getMarshaller().marshal(obj, sw);
		return sw.toString();
	}

	public XmlObject read(String xml) throws JAXBException {
		return new XmlObject(getUnmarshaller().unmarshal(new StringReader(xml)));
	}

	public XmlTransformer setFormattedOutput(boolean formattedOutput) throws PropertyException {
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

	public static void print(Object object) {
		try {
			System.out.println(XmlTransformerFactory.get().setFormattedOutput(true).build().toXmlString(object));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
