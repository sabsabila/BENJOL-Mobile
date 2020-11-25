package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.SparepartBengkelContract;
import com.alifadepe.android_example.contract.SparepartContract;
import com.alifadepe.android_example.interactor.SparepartBengkelInteractor;
import com.alifadepe.android_example.interactor.SparepartInteractor;
import com.alifadepe.android_example.model.Sparepart;

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
