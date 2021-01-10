package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.SparepartBengkelContract;
import com.benjolteam.benjol.interactor.SparepartBengkelInteractor;
import com.benjolteam.benjol.model.Sparepart;

import java.util.List;

public class SparepartBengkelPresenter implements SparepartBengkelContract.presenter {
    private SparepartBengkelContract.View view;
    private SparepartBengkelInteractor interactor;

    public SparepartBengkelPresenter(SparepartBengkelContract.View view, SparepartBengkelInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setSparepart(int id) {
        view.startLoading();
        interactor.requestSparepartBengkel(id, new RequestCallback<List<Sparepart>>() {
            @Override
            public void requestSuccess(List<Sparepart> data) {
                view.endLoading();
                view.setSparepart(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void setSearchResult(int id, String keyword) {
        view.startLoading();
        interactor.searchSparepartBengkel(id, keyword, new RequestCallback<List<Sparepart>>() {
            @Override
            public void requestSuccess(List<Sparepart> data) {
                view.endLoading();
                view.setSearchresult(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
