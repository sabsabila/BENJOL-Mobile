package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.RegisterResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.User;

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
