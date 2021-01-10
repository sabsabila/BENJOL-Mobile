package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.SparepartContract;
import com.benjolteam.benjol.interactor.SparepartInteractor;
import com.benjolteam.benjol.model.Sparepart;

import java.util.List;

public class SparepartPresenter implements SparepartContract.presenter {
    private SparepartContract.View view;
    private SparepartInteractor interactor;

    public SparepartPresenter(SparepartContract.View view, SparepartInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setSparepart() {
        view.startLoading();
        interactor.requestSparepart(new RequestCallback<List<Sparepart>>() {
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
    public void setSearchResult(String keyword) {
        view.startLoading();
        interactor.searchSparepart(keyword, new RequestCallback<List<Sparepart>>() {
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
