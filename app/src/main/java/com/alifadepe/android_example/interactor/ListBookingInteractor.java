package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.ListBengkelResponse;
import com.alifadepe.android_example.api_response.ListBookingResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListBengkelContract;
import com.alifadepe.android_example.contract.ListBookingContract;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ListBookingInteractor implements ListBookingContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListBookingInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestBookings(final RequestCallback<List<BookingData>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/allBooking")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBookingResponse.class, new ParsedRequestListener<ListBookingResponse>() {
                    @Override
                    public void onResponse(ListBookingResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.bookings);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }
}

