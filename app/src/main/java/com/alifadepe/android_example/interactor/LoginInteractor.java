package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.LoginContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public LoginInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestLogin(String email, String password, final RequestCallback<LoginResponse> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/login")
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else if(response.token == null){
                            requestCallback.requestFailed("Wrong Email or Password");
                        }
                        else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Wrong Email or Password");
                    }
                });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}

