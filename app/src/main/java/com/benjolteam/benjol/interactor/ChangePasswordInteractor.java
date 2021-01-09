package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ChangePasswordContract;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class ChangePasswordInteractor implements ChangePasswordContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ChangePasswordInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void changePassword(String[] password, final RequestCallback<String> requestCallback) {
        AndroidNetworking.put(ApiConstant.BASE_URL + "/api/user/password")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("oldPassword", password[0])
                .addBodyParameter("newPassword", password[1])
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401)
                            requestCallback.requestFailed("Old password doesn't match !");
                        else
                            requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}

