package com.alifadepe.android_example.interactor;

import com.alifadepe.android_example.contract.MainContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;

public class MainInteractor implements MainContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public MainInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        if(sharedPreferencesUtil.getToken() != null){
            return true;
        }
        else {
            return false;
        }
    }
}
