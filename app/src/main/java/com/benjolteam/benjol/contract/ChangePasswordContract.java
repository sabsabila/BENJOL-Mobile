package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;

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
