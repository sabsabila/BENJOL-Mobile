package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ListBookingResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ListBookingContract;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
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
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/booking/all")
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

