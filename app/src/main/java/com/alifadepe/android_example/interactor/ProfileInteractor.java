package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.MotorResponse;
import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ProfileContract;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ProfileInteractor implements ProfileContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProfile(final RequestCallback<List<Profile>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/user")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserResponse.class, new ParsedRequestListener<UserResponse>() {
                    @Override
                    public void onResponse(UserResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.users);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void requestMotor(final RequestCallback<List<Motorcycle>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/motorcycle")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(MotorResponse.class, new ParsedRequestListener<MotorResponse>() {
                    @Override
                    public void onResponse(MotorResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.motorcycles);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void logout() {
        sharedPreferencesUtil.clear();
    }
}

