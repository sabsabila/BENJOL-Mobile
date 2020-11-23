package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;

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
    }

    interface presenter {
        void setProfile();
        void setMotor();
        void editMotor(int id);
        void deleteMotor(int id);
        void logout();
    }

    interface Interactor {
        void requestProfile(RequestCallback<List<Profile>> requestCallback);
        void requestMotor(RequestCallback<List<Motorcycle>> requestCallback);
        void deleteMotor(int id, RequestCallback<String> requestCallback);
        void logout();
    }
}
