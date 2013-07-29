package br.com.oncast.acquirerpimp.xml.writer;

import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class XmlWriter {

	private Marshaller marshaller = null;

	private final Set<Class<?>> supportedTypes;

	private boolean formattedOutput;

	private final String encoding;

	public XmlWriter(Set<Class<?>> supportedTypes, boolean formattedOutput, String encoding) {
		this.supportedTypes = supportedTypes;
		this.formattedOutput = formattedOutput;
		this.encoding = encoding;
	}

	public void write(Object obj, OutputStream os) throws JAXBException {
		getMarshaller().marshal(obj, os);
	}

	public String asString(Object obj) throws JAXBException {
		StringWriter sw = new StringWriter();
		getMarshaller().marshal(obj, sw);
		return sw.toString();
	}

	public XmlWriter setFormattedOutput(boolean formattedOutput) throws PropertyException {
		this.formattedOutput = formattedOutput;
		if (marshaller != null) marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		return this;
	}

	private Marshaller getMarshaller() throws JAXBException, PropertyException {
		if (marshaller == null) {
			JAXBContext context = JAXBContext.newInstance(supportedTypes.toArray(new Class[supportedTypes.size()]));
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		}
		return marshaller;
	}

	public static void print(Object object) {
		try {
			System.out.println(XmlWriterFactory.get().setFormattedOutput(true).build().asString(object));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
