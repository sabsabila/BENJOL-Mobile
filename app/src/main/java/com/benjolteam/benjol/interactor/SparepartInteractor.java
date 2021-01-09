package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.SparepartResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.SparepartContract;
import com.benjolteam.benjol.model.Sparepart;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
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

