package com.sample.paymentstester;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.simplify.android.sdk.api.card.CardToken;
import com.simplify.android.sdk.api.card.TokenAssignmentListener;
import com.simplify.android.sdk.api.payment.Payment;
import com.simplify.android.sdk.api.payment.PaymentReceipt;
import com.simplify.android.sdk.api.payment.PaymentReceivedListener;

public class MasterCardTokenCallback extends AsyncTask<CardToken, Void, Void> implements TokenAssignmentListener  {

    CardToken token;
    View v;

    public MasterCardTokenCallback(View v) {
        super();
        this.v = v;
    }

    @Override
    public void tokenAssigned(CardToken token) {
        // Show success message
        String message = "Created Mastercard token" + token.getId();
        this.token = token;
        CharSequence charSequence = message.subSequence(0, message.length());

        Toast.makeText(v.getContext(), charSequence, Toast.LENGTH_LONG).show();
        Log.i("Master card Last 4", token.getCard().getLast4());

        // now charge card
        this.execute(this.token);
    }

    @Override
    public void handleError(int code, String errorMsg) {
        this.token = null;

        // Show error message
        Toast.makeText(v.getContext(),
                "Error creating Mastercard token",
                Toast.LENGTH_LONG
        ).show();
        Log.e("PaymentsTester --" + code, errorMsg);
    }

    @Override
    protected Void doInBackground(CardToken... tokens) {
        // make a charge
        Payment payment = new Payment(1000);
        payment.submitPayment(this.token, new PaymentReceivedListener() {
            @Override
            public void paymentReceived(PaymentReceipt receipt) {
                Toast.makeText(v.getContext(), "Charge completed - " + receipt, Toast.LENGTH_LONG).show();
                Log.i("Charge completed : ", receipt.toString());
            }

            @Override
            public void handleError(int statusCode, String message) {
                Toast.makeText(v.getContext(), "Charge failed!", Toast.LENGTH_LONG).show();
                Log.i("Charge failed : " + statusCode, message);
            }
        });
        return null;
    }

}
