package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.api_response.ListBookResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.ListBookContract;
import com.alifadepe.android_example.model.Book;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ListBookInteractor implements ListBookContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ListBookInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestListBook(final RequestCallback<List<Book>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "books.php")
                .addHeaders("Authorization", sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ListBookResponse.class, new ParsedRequestListener<ListBookResponse>() {
                    @Override
                    public void onResponse(ListBookResponse response) {
                        if(response != null){
                            requestCallback.requestSuccess(response.data);
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

    @Override
    public void logout() {
        sharedPreferencesUtil.clear();
    }
}
