package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ListPickupResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ListPickupContract;
import com.benjolteam.benjol.model.PickupData;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ListPickupInteractor implements ListPickupContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListPickupInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestPickups(final RequestCallback<List<PickupData>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/pickup")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListPickupResponse.class, new ParsedRequestListener<ListPickupResponse>() {
                    @Override
                    public void onResponse(ListPickupResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.pickups);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}

