package com.alifadepe.android_example.presenter;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.UploadProfileImageContract;
import com.alifadepe.android_example.interactor.UploadProfileImageInteractor;
import com.alifadepe.android_example.model.Profile;

import java.io.File;

public class UploadProfileImagePresenter implements UploadProfileImageContract.Presenter {
    private UploadProfileImageContract.View view;
    private UploadProfileImageInteractor interactor;

    public UploadProfileImagePresenter(UploadProfileImageContract.View view, UploadProfileImageInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setProfile() {
        view.startLoading();
        interactor.requestProfile(new RequestCallback<Profile>() {
            @Override
            public void requestSuccess(Profile data) {
                view.setProfile(data);
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }

    @Override
    public void saveProfile(File file) {
        view.startLoading();
        interactor.uploadImage(file, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.editProfileSuccess(message);
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
