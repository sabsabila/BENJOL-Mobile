package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.RegisterResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;;
import com.alifadepe.android_example.contract.RegisterContract;
import com.alifadepe.android_example.model.User;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class RegisterInteractor implements RegisterContract.Interactor {

    public RegisterInteractor() {

    }

    @Override
    public void requestRegister(User newUser, final RequestCallback<RegisterResponse> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/user/register")
                .addBodyParameter("full_name", newUser.getFullName())
                .addBodyParameter("phone_number", newUser.getPhoneNumber())
                .addBodyParameter("email", newUser.getEmail())
                .addBodyParameter("password", newUser.getPassword())
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
                    @Override
                    public void onResponse(RegisterResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401)
                            requestCallback.requestFailed("E-mail invalid or has already been taken.");
                        else if(anError.getErrorCode() == 500)
                            requestCallback.requestFailed("Server Error.");
                    }
                });
    }
}

