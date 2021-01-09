package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.api_response.RegisterResponse;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.User;

public interface RegisterContract {
    interface View {
        void startLoading();
        void endLoading();
        void registerSuccess();
        void registerFailed(String message);
    }

    interface Presenter {
        void register(User newUser);
    }

    interface Interactor {
        void requestRegister(User newUser, RequestCallback<RegisterResponse> requestCallback);
    }
}
