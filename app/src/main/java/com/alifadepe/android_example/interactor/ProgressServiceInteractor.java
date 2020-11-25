package com.alifadepe.android_example.interactor;

import android.util.Log;

import com.alifadepe.android_example.api_response.ProgressServiceResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.model.ProgressService;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ProgressServiceInteractor implements ProgressServiceContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProgressServiceInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProgressService(final RequestCallback<List<String>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/checkProgress")
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ProgressServiceResponse.class, new ParsedRequestListener<ProgressServiceResponse>() {
                    @Override
                    public void onResponse(ProgressServiceResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }
                        else {
                            requestCallback.requestSuccess(response.progress);
//                            Log.d("tag", response.progress.get(0));
//                            Log.d("tag", response.progress.get(1));
                            Log.d("tag", response.progress.get(2));
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
