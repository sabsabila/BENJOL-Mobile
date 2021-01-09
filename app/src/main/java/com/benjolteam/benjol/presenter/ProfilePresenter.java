package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ProfileContract;
import com.benjolteam.benjol.interactor.ProfileInteractor;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.model.Profile;

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
        view.startLoading();
        interactor.deleteMotor(id, new RequestCallback<String>(){
            @Override
            public void requestSuccess(String data) {
                view.deleteSuccess(data);
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
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }
}
