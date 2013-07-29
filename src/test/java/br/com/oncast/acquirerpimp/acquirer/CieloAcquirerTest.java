package br.com.oncast.acquirerpimp.acquirer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.oncast.acquirerpimp.bean.creditcard.CreditCard;
import br.com.oncast.acquirerpimp.bean.creditcard.CreditCardFlag;
import br.com.oncast.acquirerpimp.bean.token.CieloToken;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTokenRequestTransaction;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransaction;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;

public class CieloAcquirerTest {

	@Mock
	private CieloTransactionSender cieloRequestSender;

	private CreditCard creditCard;

	private CieloAcquirer acquirer;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		long creditCardNumber = 4012001038443335L;
		int validThru = 201508;
		String carrierName = "Fulano da Silva";
		CreditCardFlag flag = CreditCardFlag.VISA;
		creditCard = new CreditCard(creditCardNumber, validThru, carrierName, flag);

		acquirer = new CieloAcquirer(cieloRequestSender);
	}

	@Test
	public void shouldGenerateTokenWithTheGivenCreditCardFlag() throws Exception {
		CieloToken token = acquirer.generateToken(creditCard);
		assertEquals(creditCard.getFlag(), token.getCreditCardFlag());
	}

	@Test
	public void shouldSendTokenRequest() throws Exception {
		acquirer.generateToken(creditCard);
		verifyThatWasSent(CieloTokenRequestTransaction.class);
	}

	@Test
	public void shouldSendTokenWithGivenCreditCard() throws Exception {
		acquirer.generateToken(creditCard);

		CieloTokenRequestTransaction request = verifyThatWasSent(CieloTokenRequestTransaction.class);

		assertEquals(creditCard, request.getCreditCard());
	}

	private <T extends CieloTransaction<?>> T verifyThatWasSent(Class<T> type) {
		ArgumentCaptor<T> captor = ArgumentCaptor.forClass(type);
		verify(cieloRequestSender).send((CieloTransaction<?>) captor.capture());
		T request = captor.getValue();
		return request;
	}
}
