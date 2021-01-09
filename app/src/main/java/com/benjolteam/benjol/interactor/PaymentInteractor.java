package com.benjolteam.benjol.interactor;


import com.benjolteam.benjol.api_response.PaymentResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.PaymentContract;
import com.benjolteam.benjol.model.Payment;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class PaymentInteractor implements PaymentContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public PaymentInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestPayment(int id, final RequestCallback<Payment> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/payment/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(PaymentResponse.class, new ParsedRequestListener<PaymentResponse>() {
                    @Override
                    public void onResponse(PaymentResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.payment);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}
