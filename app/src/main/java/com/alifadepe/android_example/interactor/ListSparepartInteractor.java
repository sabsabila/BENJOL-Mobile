package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.ListSparepartResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListSparepartContract;
import com.alifadepe.android_example.model.Sparepart;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import android.util.Log;

import java.util.List;

public class ListSparepartInteractor implements ListSparepartContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;
    private int sparepartId;

    public ListSparepartInteractor(SharedPreferencesUtil sharedPreferencesUtil, int sparepartId) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.sparepartId = sparepartId;
    }

    @Override
    public void requestListSparepart(final RequestCallback<List<Sparepart>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/sparepart")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListSparepartResponse.class, new ParsedRequestListener<ListSparepartResponse>() {
            @Override
            public void onResponse(ListSparepartResponse response) {
                if(response != null){
                    Log.d("TESAPI", String.valueOf(response));
                    requestCallback.requestSuccess(response.data);

                }
                else {

                    requestCallback.requestFailed("Null Response");
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.d("ERROR", String.valueOf(anError));
                requestCallback.requestFailed(anError.getMessage());
            }
        });
    }

    @Override
    public void logout() {
        sharedPreferencesUtil.clear();
    }
}
