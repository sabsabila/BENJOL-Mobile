package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Motorcycle;
import com.benjolteam.benjol.model.Profile;

import java.util.List;

public interface ProfileContract {
    interface View {
        void showError(String message);
        void setProfile(Profile user);
        void showAlertDialog();
        void showDeleteAlert(int id);
        void redirectToLogin();
        void setMotor(List<Motorcycle> motorcycles);
        void editMotor(int id);
        void deleteSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void setProfile();
        void setMotor();
        void editMotor(int id);
        void deleteMotor(int id);
        void logout();
    }

    interface Interactor {
        void requestProfile(RequestCallback<Profile> requestCallback);
        void requestMotor(RequestCallback<List<Motorcycle>> requestCallback);
        void deleteMotor(int id, RequestCallback<String> requestCallback);
        void logout();
    }
}
