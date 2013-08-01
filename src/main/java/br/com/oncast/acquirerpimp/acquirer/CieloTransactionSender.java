package br.com.oncast.acquirerpimp.acquirer;

import java.io.IOException;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import br.com.oncast.acquirerpimp.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.acquirerpimp.acquirer.exception.CieloTransactionException;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionRequest;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionResponse;
import br.com.oncast.acquirerpimp.bean.transaction.TransactionIdGenerator;
import br.com.oncast.acquirerpimp.bean.transaction.error.CieloTransactionError;
import br.com.oncast.acquirerpimp.xml.XmlObject;
import br.com.oncast.acquirerpimp.xml.XmlTransformer;
import br.com.oncast.acquirerpimp.xml.XmlTransformerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloTransactionSender {

	private static final String CIELO_WS_DATA_ENCODING = "ISO-8859-1";

	private static final String CIELO_DATA_PARAMETER_NAME = "mensagem";

	private static final String CIELO_WS_VERSION = "1.2.1";

	private static final int CONNECTION_TIMEOUT = 10000;

	private final URL wsUrl;

	@Inject
	CieloTransactionSender(final CieloAcquirerConfiguration configuration) {
		wsUrl = configuration.getWebServiceUrl();
	}

	public <T extends CieloTransactionResponse> T send(final CieloTransactionRequest<T> request) throws CieloTransactionException {
		request.setId(TransactionIdGenerator.generate());
		request.setVersion(CIELO_WS_VERSION);

		final String requestXml = toXml(request);
		final String responseXml = doPost(requestXml);
		final XmlObject response = fromXml(responseXml);

		if (response.is(CieloTransactionError.class)) { throw new CieloTransactionException(response.as(CieloTransactionError.class)); }

		return response.as(request.getResponseType());
	}

	private XmlObject fromXml(final String responseXml) {
		try {
			return getXmlTransformer().read(responseXml.replaceAll(" xmlns=\".+\"", ""));
		} catch (final JAXBException e) {
			throw new RuntimeException("Could not parse response XML", e);
		}
	}

	private String toXml(final CieloTransactionRequest<?> transaction) {
		try {
			return getXmlTransformer().toXmlString(transaction);
		} catch (final JAXBException e) {
			throw new RuntimeException("Could not transform transaction data to XML", e);
		}
	}

	private String doPost(final String dataXml) {
		try {
			return Request.Post(wsUrl.toString())
					.connectTimeout(CONNECTION_TIMEOUT)
					.bodyForm(Form.form().add(CIELO_DATA_PARAMETER_NAME, dataXml).build())
					.execute()
					.returnContent().asString();
		} catch (final ClientProtocolException e) {
			throw new RuntimeException("Could not access Cielo web service", e);
		} catch (final IOException e) {
			throw new RuntimeException("Could not send transaction to Cielo web service", e);
		}
	}

	private XmlTransformer getXmlTransformer() {
		return XmlTransformerFactory.get().setEncoding(CIELO_WS_DATA_ENCODING).setFormattedOutput(false).build();
	}

}
