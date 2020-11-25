package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public interface ChatBridgeContract {
    interface View {
        void showError(String message);
        void setBengkel(Bengkel bengkel);
    }

    interface presenter {
        void setBengkel();
    }

    interface Interactor {
        void requestBengkel(RequestCallback<List<Bengkel>> requestCallback);
    }
}
