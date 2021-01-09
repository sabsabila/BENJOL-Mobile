package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.api_response.MotorResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.EditMotorcycleContract;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
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
                        requestCallback.requestSuccess(response.message);
                    }
                    @Override
                    public void onError(ANError error) {
                        requestCallback.requestFailed("Failed to save data !");
                    }
                });
    }
}

