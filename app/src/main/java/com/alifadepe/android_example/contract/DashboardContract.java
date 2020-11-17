package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.callback.RequestCallback;

public interface DashboardContract {
    interface View {
        void startLoading();
        void endLoading();
    }

    interface Presenter {
    }

    interface Interactor {
    }
}
