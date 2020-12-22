package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.PaymentResponse;
import com.alifadepe.android_example.api_response.ResponseMessage;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.UploadReceiptContract;
import com.alifadepe.android_example.model.Payment;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
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
        AndroidNetworking.upload(ApiConstant.BASE_URL + "/api/uploadReceipt/" + id)
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

