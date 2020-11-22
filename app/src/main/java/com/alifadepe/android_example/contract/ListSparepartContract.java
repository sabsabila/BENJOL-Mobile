package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Sparepart;

import java.util.List;

public interface ListSparepartContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListSparepart(List<Sparepart> spareparts);
        void showError(String errorMessage);
    }

    interface Presenter {
        void requestListSparepart();
        void logout();
    }

    interface Interactor {
        void requestListSparepart(RequestCallback<List<Sparepart>> requestCallback);
        void logout();
    }

}
