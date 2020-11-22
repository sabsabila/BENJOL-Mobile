package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Motorcycle;
import com.alifadepe.android_example.model.Profile;
import com.alifadepe.android_example.model.Sparepart;

import java.util.List;

public interface SparepartContract {
    interface View {
        void startLoading();
        void endLoading();
        void showError(String message);
        void setSparepart(List<Sparepart> spareparts);
        void setSearchresult(List<Sparepart> spareparts);
    }

    interface presenter {
        void setSparepart();
        void setSearchResult(String keyword);
    }

    interface Interactor {
        void requestSparepart(RequestCallback<List<Sparepart>> requestCallback);
        void searchSparepart(String keyword, RequestCallback<List<Sparepart>> requestCallback);
    }
}
