package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.ProgressServiceResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ProgressServiceInteractor implements ProgressServiceContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProgressServiceInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProgressService(int id, final RequestCallback<List<String>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/progress/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ProgressServiceResponse.class, new ParsedRequestListener<ProgressServiceResponse>() {
                    @Override
                    public void onResponse(ProgressServiceResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.progress);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}
