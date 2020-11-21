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
        void redirectToLogin();
        void setMotor(List<Motorcycle> motorcycles);
    }

    interface presenter {
        void setProfile();
        void setMotor();
        void logout();
    }

    interface Interactor {
        void requestProfile(RequestCallback<List<Profile>> requestCallback);
        void requestMotor(RequestCallback<List<Motorcycle>> requestCallback);
        void logout();
    }
}
