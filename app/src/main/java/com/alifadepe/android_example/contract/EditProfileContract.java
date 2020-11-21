package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.EditResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public interface EditProfileContract {
    interface View {
        void setProfile(Profile profile);
        void showError(String message);
        void editProfileSuccess(String message);
    }

    interface Presenter {
        void setProfile();
        void saveProfile(Profile profile);
    }

    interface Interactor {
        void requestProfile(RequestCallback<List<Profile>> requestCallback);
        void editProfile(Profile profile, RequestCallback<String> requestCallback);
    }
}
