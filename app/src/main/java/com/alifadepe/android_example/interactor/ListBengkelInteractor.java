package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.BengkelResponse;
import com.alifadepe.android_example.api_response.ListBengkelResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListBengkelContract;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ListBengkelInteractor implements ListBengkelContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListBengkelInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestBengkel(final RequestCallback<List<Bengkel>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/bengkel/all")
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

    @Override
    public void searchBengkel(String keyword, final RequestCallback<List<Bengkel>> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/bengkel/search")
                .addBodyParameter("name", keyword)
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
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}

