package com.alifadepe.android_example.interactor;


import com.alifadepe.android_example.api_response.PaymentResponse;
import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.contract.PaymentContract;
import com.alifadepe.android_example.model.Payment;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

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
