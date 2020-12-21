package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Profile;

import java.io.File;

public interface ChangePasswordContract {
    interface View {
        void showError(String message);
        void changePasswordSuccess(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void savePassword(String[] password);
    }

    interface Interactor {
        void changePassword(String[] password, RequestCallback<String> requestCallback);
    }
}
