package com.sample.paymentstester;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.compat.AsyncTask;
import com.stripe.android.model.Token;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

public class StripeTokenCallback extends TokenCallback {

    Token token;
    View v;

    public StripeTokenCallback(View v) {
        super();
        this.v = v;
    }

    public void onSuccess(final Token token) {
        // Show success message
        String message = "Created Stripe token" + token.getId();
        this.token = token;
        CharSequence charSequence = message.subSequence(0, message.length());

        Toast.makeText(v.getContext(), charSequence, Toast.LENGTH_LONG).show();
        Log.i("Credit card Last 4", token.getCard().getLast4());

        new AsyncTask<Token, Void, Void>() {
            @Override
            protected Void doInBackground(Token... params) {
                // now charge card
                chargeCard(v, token);
                return null;
            }
        }.execute(this.token);
    }
    public void onError(Exception error) {
        this.token = null;

        // Show error message
        Toast.makeText(v.getContext(),
                "Error creating stripe token",
                Toast.LENGTH_LONG
        ).show();
        Log.e("PaymentsTester", error.getMessage(), error);
    }

    private void chargeCard(View v, Token token) {
        // charge the card
        com.stripe.Stripe.apiKey = "sk_test_6m2E8BSNk4prVf3ltPIkO89j";
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 1000);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();

        // fetch card params from stripe token
        cardMap.put("number", token.getCard().getNumber());
        cardMap.put("exp_month", token.getCard().getExpMonth());
        cardMap.put("exp_year", token.getCard().getExpYear());
        cardMap.put("cvc", token.getCard().getCVC());
        chargeMap.put("card", cardMap);
        try {
            Charge charge = Charge.create(chargeMap);
            Toast.makeText(v.getContext(), "Charge completed - " + charge, Toast.LENGTH_LONG).show();
            Log.i("Charge completed : ", charge.toString());
        } catch (StripeException e) {
            e.printStackTrace();
            Log.e("Charge failed : ", e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Stripe charge failed: ", e.getMessage(), e);
        }
    }
}
