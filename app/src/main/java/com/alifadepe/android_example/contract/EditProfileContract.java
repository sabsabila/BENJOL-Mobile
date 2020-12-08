package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public interface EditProfileContract {
    interface View {
        void setProfile(Profile profile);
        void showError(String message);
        void editProfileSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setProfile();
        void saveProfile(Profile profile, String[] password);
    }

    interface Interactor {
        void requestProfile(RequestCallback<Profile> requestCallback);
        void editProfile(Profile profile, String[] password, RequestCallback<String> requestCallback);
    }
}
