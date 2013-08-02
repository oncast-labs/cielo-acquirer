package br.com.oncast.cieloacquirer.bean.payment.token;

import br.com.oncast.cieloacquirer.bean.payment.creditcard.CardFlag;
import br.com.oncast.cieloacquirer.utils.NumberStringUtils;

public class PaymentTokenUtils {

	private static int counter = 0;

	public static PaymentToken create() {
		return new PaymentToken(new TokenData("token" + ++counter, TokenStatus.UNBLOCKED, "455187******" + NumberStringUtils.formatWithLeadingZeros(counter, 4)), CardFlag.VISA);
	}

}
