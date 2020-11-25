package com.alifadepe.android_example.presenter;

import android.util.Log;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ProgressServiceContract;
import com.alifadepe.android_example.interactor.ProgressServiceInteractor;
import com.alifadepe.android_example.model.ProgressService;

import java.text.ParseException;
import java.util.List;

public class ProgressServicePresenter implements ProgressServiceContract.Presenter {
    private ProgressServiceContract.View view;
    private ProgressServiceInteractor interactor;

    public ProgressServicePresenter(ProgressServiceContract.View view, ProgressServiceInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setProgressService() {
        interactor.requestProgressService(new RequestCallback<List<String>>() {
            @Override
            public void requestSuccess(List<String> progress) {
                try {
                    Log.d("tag", "ini masuk presenter");
                    view.setProgressService(progress);
                    Log.d("tag", "setelah panggil method view");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void payService() {
        view.redirectToPayment();
    }
}
