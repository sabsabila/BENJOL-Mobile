package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.Sparepart;

import java.util.List;

public interface SparepartBengkelContract {
    interface View {
        void startLoading();
        void endLoading();
        void showError(String message);
        void setSparepart(List<Sparepart> spareparts);
        void setSearchresult(List<Sparepart> spareparts);
    }

    interface presenter {
        void setSparepart(int id);
        void setSearchResult(int id, String keyword);
    }

    interface Interactor {
        void requestSparepartBengkel(int id, RequestCallback<List<Sparepart>> requestCallback);
        void searchSparepartBengkel(int id, String keyword, RequestCallback<List<Sparepart>> requestCallback);
    }
}
