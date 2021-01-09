package com.benjolteam.benjol.interactor;


import com.benjolteam.benjol.api_response.BookingResponse;
import com.benjolteam.benjol.api_response.UserResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.DashboardContract;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.model.Profile;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class DashboardInteractor implements DashboardContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public DashboardInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void getUser(final RequestCallback<Profile> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/user")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(UserResponse.class, new ParsedRequestListener<UserResponse>() {
                    @Override
                    public void onResponse(UserResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.user);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }

    @Override
    public void getLatestBooking(final RequestCallback<BookingData> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/booking")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(BookingResponse.class, new ParsedRequestListener<BookingResponse>() {
                    @Override
                    public void onResponse(BookingResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.booking);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
