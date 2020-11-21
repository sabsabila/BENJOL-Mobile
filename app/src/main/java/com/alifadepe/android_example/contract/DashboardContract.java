package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.UserResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.model.User;

import java.util.List;

public interface DashboardContract {
    interface View {
        void setUsername(String firstName);
        void showError(String message);
    }

    interface Presenter {
        void getUsername();
    }

    interface Interactor {
        void getUser(final RequestCallback<List<Profile>> requestCallback);
    }
}
