package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public interface ListBengkelContract {
    interface View {
        void startLoading();
        void endLoading();
        void showError(String message);
        void setBengkel(List<Bengkel> bengkel);
        void setSearchresult(List<Bengkel> bengkel);
    }

    interface presenter {
        void setBengkel();
        void setSearchResult(String keyword);
    }

    interface Interactor {
        void requestBengkel(RequestCallback<List<Bengkel>> requestCallback);
        void searchBengkel(String keyword, RequestCallback<List<Bengkel>> requestCallback);
    }
}
