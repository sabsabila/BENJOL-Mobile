package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Bengkel;

import java.util.List;

public interface ChatBridgeContract {
    interface View {
        void showError(String message);
        void setBengkel(Bengkel bengkel);
        void startLoading();
        void endLoading();
    }

    interface presenter {
        void setBengkel();
    }

    interface Interactor {
        void requestBengkel(RequestCallback<List<Bengkel>> requestCallback);
    }
}
