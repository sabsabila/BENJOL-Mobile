package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.callback.RequestCallback;

public interface LoginContract {
    interface View {
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
    }

    interface Presenter {
        void login(String username, String password);
    }

    interface Interactor {
        void requestLogin(String username, String password, RequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
    }
}
