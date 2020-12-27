package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.BengkelResponse;
import com.alifadepe.android_example.api_response.ListBengkelResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.DetailBengkelContract;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class DetailBengkelInteractor implements DetailBengkelContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private int bengkelId;

    public DetailBengkelInteractor(SharedPreferencesUtil sharedPreferencesUtil, int bengkelId) {
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
                        }
                        else {
                            requestCallback.requestSuccess(response.bengkel);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}
