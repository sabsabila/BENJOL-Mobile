package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.MotorcycleContract;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

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
                .addBodyParameter("plate_number", motorcycle.getPlate_number())
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        requestCallback.requestSuccess(response.message);
                    }
                    @Override
                    public void onError(ANError error) {
                        requestCallback.requestFailed("Failed to save data !");
                    }
                });
    }
}

