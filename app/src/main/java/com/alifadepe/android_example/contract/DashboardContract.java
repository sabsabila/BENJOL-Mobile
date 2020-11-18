package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;

public interface DashboardContract {
    interface View {
        void startLoading();
        void endLoading();
        void setFirstName(String firstName);
        void showError(String message);
    }

    interface Presenter {
        void getFirstName();
    }

    interface Interactor {
        void getUser(final RequestCallback<UserResponse> requestCallback);
    }
}
