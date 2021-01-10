package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ListBengkelResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ChatBridgeContract;
import com.benjolteam.benjol.model.Bengkel;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
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

