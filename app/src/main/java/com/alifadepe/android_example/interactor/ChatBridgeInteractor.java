package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.BengkelResponse;
import com.alifadepe.android_example.api_response.ListBengkelResponse;
import com.alifadepe.android_example.api_response.ListMotorResponse;
import com.alifadepe.android_example.api_response.ResponseMessage;
import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ChatBridgeContract;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ChatBridgeInteractor implements ChatBridgeContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private int bengkelId;

    public ChatBridgeInteractor(SharedPreferencesUtil sharedPreferencesUtil, int bengkelId) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.bengkelId = bengkelId;
    }

    @Override
    public void requestBengkel(final RequestCallback<List<Bengkel>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/bengkel/" + bengkelId)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBengkelResponse.class, new ParsedRequestListener<ListBengkelResponse>() {
                    @Override
                    public void onResponse(ListBengkelResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.bengkel);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }
}

