package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Profile;

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
        void saveProfile(Profile profile);
    }

    interface Interactor {
        void requestProfile(RequestCallback<Profile> requestCallback);
        void editProfile(Profile profile, RequestCallback<String> requestCallback);
    }
}
