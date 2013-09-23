/*
 * Copyright (c) 2013, Asynchrony Solutions, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 *     * Neither the name of Asynchrony nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL ASYNCHRONY SOLUTIONS, INC. BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.simplify.android.sdk.api.payment;

import com.simplify.android.sdk.api.AsyncApiRequest;
import com.simplify.android.sdk.api.card.CardToken;
import org.apache.http.client.HttpResponseException;

/**
 * Used by a <code>Payment</code> to makes an asynchronous call to the <strong>Simplify.com</strong>
 * API to submit a payment on the given <code>Card</code> represented by the server-assigned token,
 * returning a receipt to application code via a listener interface.
 *
 * @see com.simplify.android.sdk.api.card.CardToken
 * @see com.simplify.android.sdk.api.payment.Payment
 */
class PaymentByCardTokenRequest extends AsyncApiRequest<CardToken, PaymentReceipt, PaymentReceivedListener> {

    public PaymentByCardTokenRequest(PaymentReceivedListener listener) {
        super(listener);
    }

    @Override
    protected PaymentReceipt callServer(CardToken... params) throws HttpResponseException {
        return null;
    }

    @Override
    protected void notifyDataReceived(PaymentReceivedListener listener, PaymentReceipt returnData) {
        listener.paymentReceived(returnData);
    }
}
