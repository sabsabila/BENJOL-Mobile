package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.SparepartResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.SparepartBengkelContract;
import com.benjolteam.benjol.model.Sparepart;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
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
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/sparepart/bengkel/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
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
    public void searchSparepartBengkel(int id, String keyword, final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/sparepart/bengkel/search/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
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

