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
        interactor.requestProfile(new RequestCallback<List<Profile>>() {
            @Override
            public void requestSuccess(List<Profile> data) {
                view.setProfile(data.get(0));
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void saveProfile(Profile profile) {
        interactor.editProfile(profile, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.editProfileSuccess(message);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
