package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.ResponseMessage;
import com.alifadepe.android_example.api_response.MotorResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.EditMotorcycleContract;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class EditMotorcycleInteractor implements EditMotorcycleContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public EditMotorcycleInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void showMotorById(int id, final RequestCallback<Motorcycle> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/motorcycle/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(MotorResponse.class, new ParsedRequestListener<MotorResponse>() {
                    @Override
                    public void onResponse(MotorResponse response) {
                        Log.d("tag", "sukses");
                        requestCallback.requestSuccess(response.motorcycles);
                    }
                    @Override
                    public void onError(ANError error) {
                        requestCallback.requestFailed(error.getMessage());
                    }
                });
    }

    @Override
    public void editMotorRequest(final int id, Motorcycle motorcycle, final RequestCallback<String> requestCallback) {
        AndroidNetworking.put(ApiConstant.BASE_URL + "/api/motorcycle/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("brand", motorcycle.getBrand())
                .addBodyParameter("plate_number", motorcycle.getPlate_number())
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        Log.d("motorcycle", String.valueOf(id));
                        requestCallback.requestSuccess(response.message);
                    }
                    @Override
                    public void onError(ANError error) {
                        requestCallback.requestFailed(error.getMessage());
                    }
                });
    }
}

