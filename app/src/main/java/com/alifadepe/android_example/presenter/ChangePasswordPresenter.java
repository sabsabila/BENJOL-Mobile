package com.alifadepe.android_example.presenter;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ChangePasswordContract;
import com.alifadepe.android_example.interactor.ChangePasswordInteractor;
import com.alifadepe.android_example.model.Profile;

import java.io.File;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {
    private ChangePasswordContract.View view;
    private ChangePasswordInteractor interactor;

    public ChangePasswordPresenter(ChangePasswordContract.View view, ChangePasswordInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void savePassword(String[] password) {
        view.startLoading();
        interactor.changePassword(password, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.changePasswordSuccess(message);
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }
}
