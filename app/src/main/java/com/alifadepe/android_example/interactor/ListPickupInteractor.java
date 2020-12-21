package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.ListBookingResponse;
import com.alifadepe.android_example.api_response.ListPickupResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListBookingContract;
import com.alifadepe.android_example.contract.ListPickupContract;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.PickupData;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
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
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/allPickup")
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

