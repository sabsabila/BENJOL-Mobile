package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.EditProfileContract;
import com.alifadepe.android_example.contract.ProfileContract;
import com.alifadepe.android_example.interactor.EditProfileInteractor;
import com.alifadepe.android_example.interactor.ProfileInteractor;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public class EditProfilePresenter implements EditProfileContract.Presenter {
    private EditProfileContract.View view;
    private EditProfileInteractor interactor;

    public EditProfilePresenter(EditProfileContract.View view, EditProfileInteractor interactor) {
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
    public void saveProfile(Profile profile) {
        view.startLoading();
        interactor.editProfile(profile, new RequestCallback<String>() {
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
