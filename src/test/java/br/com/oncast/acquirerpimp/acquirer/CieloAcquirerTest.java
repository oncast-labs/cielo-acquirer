package br.com.oncast.acquirerpimp.acquirer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.oncast.acquirerpimp.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.acquirerpimp.acquirer.exception.CieloTransactionException;
import br.com.oncast.acquirerpimp.bean.payment.creditcard.CardFlag;
import br.com.oncast.acquirerpimp.bean.payment.creditcard.PaymentCard;
import br.com.oncast.acquirerpimp.bean.payment.token.PaymentToken;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionRequest;
import br.com.oncast.acquirerpimp.bean.transaction.token.GenerateTokenTransactionRequest;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;

public class CieloAcquirerTest {

	@Mock
	private CieloTransactionSender cieloRequestSender;

	private PaymentCard creditCard;

	private CieloAcquirer acquirer;

	@Mock
	private CieloAcquirerConfiguration configuration;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		final long creditCardNumber = 4012001038443335L;
		final int validThru = 201508;
		final String carrierName = "Fulano da Silva";
		final CardFlag flag = CardFlag.VISA;
		creditCard = new PaymentCard(creditCardNumber, validThru, carrierName, flag);

		acquirer = new CieloAcquirer(configuration, cieloRequestSender);
	}

	@Test
	public void shouldGenerateTokenWithTheGivenCreditCardFlag() throws Exception {
		final PaymentToken token = acquirer.generateToken(creditCard);
		assertEquals(creditCard.getCardFlag(), token.getCardFlag());
	}

	@Test
	public void shouldSendTokenRequest() throws Exception {
		acquirer.generateToken(creditCard);
		verifyThatWasSent(GenerateTokenTransactionRequest.class);
	}

	@Test
	public void shouldSendTokenWithGivenCreditCard() throws Exception {
		acquirer.generateToken(creditCard);

		final GenerateTokenTransactionRequest request = verifyThatWasSent(GenerateTokenTransactionRequest.class);

		assertEquals(creditCard, request.getCreditCard());
	}

	private <T extends CieloTransactionRequest<?>> T verifyThatWasSent(final Class<T> type) throws CieloTransactionException {
		final ArgumentCaptor<T> captor = ArgumentCaptor.forClass(type);
		verify(cieloRequestSender).send((CieloTransactionRequest<?>) captor.capture());
		final T request = captor.getValue();
		return request;
	}
}
