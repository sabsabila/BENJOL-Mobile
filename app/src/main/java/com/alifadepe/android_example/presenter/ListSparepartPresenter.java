package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ListSparepartContract;
import com.alifadepe.android_example.model.Sparepart;

import java.util.List;

public class ListSparepartPresenter implements ListSparepartContract.Presenter {
    private ListSparepartContract.View view;
    private ListSparepartContract.Interactor interactor;

    public ListSparepartPresenter(ListSparepartContract.View view, ListSparepartContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestListSparepart() {
        view.startLoading();
        interactor.requestListSparepart(new RequestCallback<List<Sparepart>>() {
            @Override
            public void requestSuccess(List<Sparepart> data) {
                view.endLoading();
                view.showListSparepart(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void logout() {
        interactor.logout();
    }
}
