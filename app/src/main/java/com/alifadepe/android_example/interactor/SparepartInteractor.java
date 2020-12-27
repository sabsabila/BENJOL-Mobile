package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.SparepartResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.SparepartContract;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class SparepartInteractor implements SparepartContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public SparepartInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestSparepart(final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/sparepart")
                .build()
                .getAsObject(SparepartResponse.class, new ParsedRequestListener<SparepartResponse>() {
                    @Override
                    public void onResponse(SparepartResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.spareparts);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }

    @Override
    public void searchSparepart(String keyword, final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/sparepart/search")
                .addBodyParameter("name", keyword)
                .build()
                .getAsObject(SparepartResponse.class, new ParsedRequestListener<SparepartResponse>() {
                    @Override
                    public void onResponse(SparepartResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.spareparts);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}

