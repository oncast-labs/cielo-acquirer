package br.com.oncast.cieloacquirer.acquirer;

import java.net.URL;

import br.com.oncast.cieloacquirer.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionException;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionRequest;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionResponse;
import br.com.oncast.cieloacquirer.bean.transaction.TransactionIdGenerator;
import br.com.oncast.cieloacquirer.bean.transaction.error.CieloTransactionError;
import br.com.oncast.cieloacquirer.http.HttpPostSender;
import br.com.oncast.cieloacquirer.xml.XmlObject;
import br.com.oncast.cieloacquirer.xml.XmlTransformer;
import br.com.oncast.cieloacquirer.xml.XmlTransformerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloTransactionSender {

	private static final String CIELO_WS_DATA_ENCODING = "ISO-8859-1";

	private static final String CIELO_DATA_PARAMETER_NAME = "mensagem";

	private static final String CIELO_WS_VERSION = "1.2.1";

	private final URL wsUrl;

	private final XmlTransformer transformer;

	@Inject
	CieloTransactionSender(final CieloAcquirerConfiguration configuration) {
		wsUrl = configuration.getWebServiceUrl();
		transformer = XmlTransformerFactory.get().setEncoding(CIELO_WS_DATA_ENCODING).setFormattedOutput(false).build();
	}

	public <T extends CieloTransactionResponse> T send(final CieloTransactionRequest<T> request) throws CieloTransactionException {
		request.setId(TransactionIdGenerator.generate());
		request.setVersion(CIELO_WS_VERSION);

		String responseXml = doPost(transformer.toXml(request));
		responseXml = transformer.removeNamespace(responseXml);
		final XmlObject response = transformer.fromXml(responseXml);

		if (response.is(CieloTransactionError.class)) { throw new CieloTransactionException(response.as(CieloTransactionError.class)); }

		return response.as(request.getResponseType());
	}

	private String doPost(final String dataXml) {
		return new HttpPostSender(wsUrl.toString())
				.addParam(CIELO_DATA_PARAMETER_NAME, dataXml)
				.doPost();
	}

}
