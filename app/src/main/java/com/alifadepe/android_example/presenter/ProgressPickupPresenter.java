package com.alifadepe.android_example.presenter;

import android.util.Log;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ProgressPickupContract;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.interactor.ProgressPickupInteractor;
import com.alifadepe.android_example.interactor.ProgressServiceInteractor;
import com.alifadepe.android_example.model.Pickup;

import java.text.ParseException;
import java.util.List;

public class ProgressPickupPresenter implements ProgressPickupContract.Presenter {
    private ProgressPickupContract.View view;
    private ProgressPickupInteractor interactor;

    public ProgressPickupPresenter(ProgressPickupContract.View view, ProgressPickupInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setProgressService() {
        interactor.requestProgressPickup(new RequestCallback<Pickup>() {
            @Override
            public void requestSuccess(Pickup pickup) {
                view.setProgressPickup(pickup);
            }
            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
