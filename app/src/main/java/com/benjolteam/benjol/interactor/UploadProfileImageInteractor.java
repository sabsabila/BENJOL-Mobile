package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.api_response.UserResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.UploadProfileImageContract;
import com.benjolteam.benjol.model.Profile;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.io.File;

public class UploadProfileImageInteractor implements UploadProfileImageContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public UploadProfileImageInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProfile(final RequestCallback<Profile> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/user")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserResponse.class, new ParsedRequestListener<UserResponse>() {
                    @Override
                    public void onResponse(UserResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.user);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }

    @Override
    public void uploadImage(File file, final  RequestCallback<String> requestCallback) {
        AndroidNetworking.upload(ApiConstant.BASE_URL + "/api/user/image")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addMultipartFile("profile_picture", file)
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        requestCallback.requestSuccess(response.message);
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("failed to upload image");
                    }
                });
    }
}

