package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Profile;

import java.io.File;

public interface UploadProfileImageContract {
    interface View {
        void setProfile(Profile profile);
        void showError(String message);
        void editProfileSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setProfile();
        void saveProfile(File file);
    }

    interface Interactor {
        void requestProfile(RequestCallback<Profile> requestCallback);
        void uploadImage(File file, RequestCallback<String> requestCallback);
    }
}
