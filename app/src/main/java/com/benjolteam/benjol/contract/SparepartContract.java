package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Sparepart;

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
