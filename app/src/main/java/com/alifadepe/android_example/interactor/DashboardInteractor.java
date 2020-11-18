package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.DashboardContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class DashboardInteractor implements DashboardContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public DashboardInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void getUser(final RequestCallback<UserResponse> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/user")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserResponse.class, new ParsedRequestListener<UserResponse>() {
                    @Override
                    public void onResponse(UserResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
