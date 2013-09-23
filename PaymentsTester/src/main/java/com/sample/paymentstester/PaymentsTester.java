package com.sample.paymentstester;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.stripe.android.Stripe;
import com.stripe.android.model.Card;

public class PaymentsTester extends Activity {

    public static final String STRIPE_API_KEY = "pk_test_hWnSOhjKgBlfSj7dfj5aFnMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payments_tester, menu);
        return true;
    }

    public void onStripeButtonClick(View v) throws com.stripe.exception.AuthenticationException {
        // create a new dummy card
        Card card = new Card("4111111111111111", 12, 2014, "123");

        // create a token
        Stripe stripe = new Stripe(STRIPE_API_KEY);
        StripeTokenCallback stripeTokenCallback = new StripeTokenCallback(v);
        stripe.createToken(card, stripeTokenCallback);
    }

    public void onMasterCardButtonClick(View v) {
        // create a dummy card
        // yeah, classname collisions..
        com.simplify.android.sdk.api.card.Card masterCard = new
                com.simplify.android.sdk.api.card.Card("4111111111111111", "123", 2014, 12);

        MasterCardTokenCallback masterCardTokenCallback = new MasterCardTokenCallback(v);
        masterCard.requestToken(masterCardTokenCallback);
    }
}
