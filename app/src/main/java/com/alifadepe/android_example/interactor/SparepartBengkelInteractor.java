package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.SparepartResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.SparepartBengkelContract;
import com.alifadepe.android_example.contract.SparepartContract;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class SparepartBengkelInteractor implements SparepartBengkelContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public SparepartBengkelInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestSparepartBengkel(final int id, final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/sparepartBengkel/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(SparepartResponse.class, new ParsedRequestListener<SparepartResponse>() {
                    @Override
                    public void onResponse(SparepartResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.spareparts);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void searchSparepartBengkel(int id, String keyword, final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/searchSparepartBengkel/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("name", keyword)
                .build()
                .getAsObject(SparepartResponse.class, new ParsedRequestListener<SparepartResponse>() {
                    @Override
                    public void onResponse(SparepartResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.spareparts);
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

