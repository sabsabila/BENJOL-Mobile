package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.api_response.LoginResponse;
import com.benjolteam.benjol.callback.RequestCallback;

public interface LoginContract {
    interface View {
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
        void register();
    }

    interface Presenter {
        void login(String email, String password);
        void register();
    }

    interface Interactor {
        void requestLogin(String email, String password, RequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
    }
}
