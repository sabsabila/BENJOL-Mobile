package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.AddMotorResponse;
import com.alifadepe.android_example.api_response.RegisterResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.MotorcycleContract;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;

public class MotorcycleInteractor implements MotorcycleContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public MotorcycleInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void addMotorRequest(Motorcycle motorcycle, final RequestCallback<String> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/motorcycle")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("brand", motorcycle.getBrand())
                .addBodyParameter("plate_number", motorcycle.getPlateNumber())
                .build()
                .getAsObject(AddMotorResponse.class, new ParsedRequestListener<AddMotorResponse>() {
                    @Override
                    public void onResponse(AddMotorResponse response) {
                        Log.d("tag", response.message);
                        requestCallback.requestSuccess(response.message);
                    }
                    @Override
                    public void onError(ANError error) {
                        requestCallback.requestFailed(error.getMessage());
                    }
                });
    }
}

