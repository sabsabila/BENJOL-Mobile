package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.PaymentResponse;
import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.UploadReceiptContract;
import com.benjolteam.benjol.model.Payment;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.io.File;

public class UploadReceiptInteractor implements UploadReceiptContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public UploadReceiptInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestReceipt(int id, final RequestCallback<Payment> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/payment/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(PaymentResponse.class, new ParsedRequestListener<PaymentResponse>() {
                    @Override
                    public void onResponse(PaymentResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.payment);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }

    @Override
    public void uploadImage(int id, File file, final  RequestCallback<String> requestCallback) {
        AndroidNetworking.upload(ApiConstant.BASE_URL + "/api/upload/receipt/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addMultipartFile("receipt", file)
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        requestCallback.requestSuccess(response.message);
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("failed to upload image");
                    }
                });
    }
}

