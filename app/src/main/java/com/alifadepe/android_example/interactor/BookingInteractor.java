package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.BookingResponse;
import com.alifadepe.android_example.api_response.ListMotorResponse;
import com.alifadepe.android_example.api_response.ServiceResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.BookingContract;
import com.alifadepe.android_example.model.Booking;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Service;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class BookingInteractor implements BookingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private int bengkelId;

    public BookingInteractor(SharedPreferencesUtil sharedPreferencesUtil, int bengkelId) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.bengkelId = bengkelId;
    }

    @Override
    public void requestBooking(Booking newBooking, final RequestCallback<String> requestCallback) {
        AndroidNetworking.post(ApiConstant.BASE_URL + "/api/booking")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("bengkel_id", String.valueOf(newBooking.getBengkelId()))
                .addBodyParameter("motorcycle_id", String.valueOf(newBooking.getMotorcycleId()))
                .addBodyParameter("repairment_date", newBooking.getRepairmentDate())
                .addBodyParameter("repairment_note", newBooking.getRepairmentNote())
                .addBodyParameter("isPickup", newBooking.getIsPickup())
                .addBodyParameter("pickup_location", newBooking.getPickupLocation())
                .addBodyParameter("dropoff_location", newBooking.getDropOffLocation())
                .addBodyParameter("service_id", String.valueOf(newBooking.getSelectedService()))
                .build()
                .getAsObject(BookingResponse.class, new ParsedRequestListener<BookingResponse>() {
                    @Override
                    public void onResponse(BookingResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void requestMotor(final RequestCallback<List<Motorcycle>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/motorcycle")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListMotorResponse.class, new ParsedRequestListener<ListMotorResponse>() {
                    @Override
                    public void onResponse(ListMotorResponse response) {
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
    public void requestService(final RequestCallback<List<Service>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/service/" + bengkelId)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ServiceResponse.class, new ParsedRequestListener<ServiceResponse>() {
                    @Override
                    public void onResponse(ServiceResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.services);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }
}
