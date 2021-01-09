package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.PickupResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ProgressPickupContract;
import com.benjolteam.benjol.model.Pickup;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class ProgressPickupInteractor implements ProgressPickupContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProgressPickupInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProgressPickup(int id, final RequestCallback<Pickup> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/pickup/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(PickupResponse.class, new ParsedRequestListener<PickupResponse>() {
                    @Override
                    public void onResponse(PickupResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.pickup);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}
