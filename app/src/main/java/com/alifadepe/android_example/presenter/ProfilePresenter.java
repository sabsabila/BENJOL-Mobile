package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ProfileContract;
import com.alifadepe.android_example.interactor.ProfileInteractor;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public class ProfilePresenter implements ProfileContract.presenter {
    private ProfileContract.View view;
    private ProfileInteractor interactor;

    public ProfilePresenter(ProfileContract.View view, ProfileInteractor interactor) {
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
    public void setMotor() {
        interactor.requestMotor(new RequestCallback<List<Motorcycle>>() {
            @Override
            public void requestSuccess(List<Motorcycle> data) {
                view.setMotor(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void editMotor(int id) {
        view.editMotor(id);
    }

    @Override
    public void deleteMotor(int id) {
        interactor.deleteMotor(id, new RequestCallback<String>(){
            @Override
            public void requestSuccess(String data) {
                view.deleteSuccess(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }
}
