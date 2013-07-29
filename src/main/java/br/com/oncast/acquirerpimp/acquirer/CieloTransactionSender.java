package br.com.oncast.acquirerpimp.acquirer;

import java.io.IOException;
import java.net.URI;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import br.com.oncast.acquirerpimp.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.acquirerpimp.acquirer.exception.CieloTransactionException;
import br.com.oncast.acquirerpimp.bean.establishment.Establishment;
import br.com.oncast.acquirerpimp.bean.transaction.CieloResponse;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransaction;
import br.com.oncast.acquirerpimp.xml.writer.XmlWriter;
import br.com.oncast.acquirerpimp.xml.writer.XmlWriterFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloTransactionSender {

	private static final String CIELO_WS_DATA_ENCODING = "ISO-8859-1";

	private static final String CIELO_DATA_PARAMETER_NAME = "mensagem";

	private static final int CONNECTION_TIMEOUT = 10000;

	private final URI wsUrl;

	private final Establishment establishment;

	@Inject
	CieloTransactionSender(CieloAcquirerConfiguration configuration) {
		wsUrl = configuration.getUrl();
		establishment = configuration.getEstablishment();
	}

	public <T extends CieloResponse> T send(CieloTransaction<T> request) {
		request.setEstablishment(establishment);
		String responseAsString = callWS(request);

		if (isError(responseAsString)) throw new CieloTransactionException();
		return request.getResponseBuilder().fromXmlString(responseAsString);

	}

	private boolean isError(String responseAsString) {
		return false;
	}

	private String callWS(CieloTransaction<?> transaction) {
		try {
			String dataXml = getXmlWriter().asString(transaction);
			System.out.println("Sending transaction...");
			XmlWriter.print(transaction);
			String responseXml = Request.Post(wsUrl)
					.connectTimeout(CONNECTION_TIMEOUT)
					.bodyForm(Form.form().add(CIELO_DATA_PARAMETER_NAME, dataXml).build())
					.execute()
					.returnContent().asString();
			System.out.println("Received response");
			System.out.println(responseXml);
			return responseXml;
		} catch (ClientProtocolException e) {
			throw new RuntimeException("Error while trying to access Cielo web service", e);
		} catch (IOException e) {
			throw new RuntimeException("Error while trying to send transaction to Cielo web service", e);
		} catch (JAXBException e) {
			throw new RuntimeException("Error while trying serialize Cielo transaction data to XML", e);
		}

	}

	private XmlWriter getXmlWriter() {
		return XmlWriterFactory.get().setEncoding(CIELO_WS_DATA_ENCODING).setFormattedOutput(false).build();
	}

}
