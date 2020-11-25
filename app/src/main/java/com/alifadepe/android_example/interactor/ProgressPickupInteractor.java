package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.PickupResponse;
import com.alifadepe.android_example.api_response.ProgressServiceResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ProgressPickupContract;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.model.Pickup;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import java.util.List;

public class ProgressPickupInteractor implements ProgressPickupContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProgressPickupInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProgressPickup(final RequestCallback<Pickup> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/pickup")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(PickupResponse.class, new ParsedRequestListener<PickupResponse>() {
                    @Override
                    public void onResponse(PickupResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.pickup);
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
