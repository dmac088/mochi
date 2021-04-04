package io.nzbee.payment;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class PaymentService {

    
	@Value("${STRIPE_SECRET_KEY}")
    private String API_SECRET_KEY;

    
    public String charge(PaymentRequest chargeRequest) throws Exception {
    	Stripe.apiKey = API_SECRET_KEY;
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(chargeRequest.getAmount() * 100));
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("source", chargeRequest.getToken());
        Charge charge = Charge.create(chargeParams);
        return charge.getId();
    }
}