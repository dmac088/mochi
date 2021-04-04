package io.nzbee.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Value("${STRIPE_SECRET_KEY}")
	private String API_SECRET_KEY;

	private static class CreatePaymentIntentRequest {

		@JsonProperty("currency")
		private String currency;

		public String getCurrency() {
			return currency;
		}

		@JsonProperty("paymentMethodType")
		private String paymentMethodType;

		public String getPaymentMethodType() {
			return paymentMethodType;
		}
	}

	@PostMapping("/create-payment-intent")
	public void createPaymentIntent() {
		Stripe.apiKey = API_SECRET_KEY;

		try {
			CreatePaymentIntentRequest data = gson.fromJson(request.body(), CreatePaymentIntentRequest.class);

			PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(1999L)
					.setCurrency(data.getCurrency()).addPaymentMethodType(data.getPaymentMethodType()).build();

			PaymentIntent paymentIntent = PaymentIntent.create(params);
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("clientSecret", paymentIntent.getClientSecret());
			return gson.toJson(responseData);
		} catch (StripeException e) {
			Map<String, Object> errorData = new HashMap<>();
			errorData.put("message", e.getUserMessage());

			Map<String, Object> responseData = new HashMap<>();
			responseData.put("error", errorData);

			response.status(400);
			return gson.toJson(responseData);
		} catch (Exception e) {
			Map<String, Object> errorData = new HashMap<>();
			errorData.put("message", e.getMessage());

			Map<String, Object> responseData = new HashMap<>();
			responseData.put("error", errorData);

			response.status(500);
			return gson.toJson(responseData);
		}

	}

}