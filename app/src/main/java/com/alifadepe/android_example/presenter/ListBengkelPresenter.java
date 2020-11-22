package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ListBengkelContract;
import com.alifadepe.android_example.interactor.ListBengkelInteractor;
import com.alifadepe.android_example.model.Bengkel;

import java.util.List;

public class ListBengkelPresenter implements ListBengkelContract.presenter {
    private ListBengkelContract.View view;
    private ListBengkelInteractor interactor;

    public ListBengkelPresenter(ListBengkelContract.View view, ListBengkelInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setBengkel() {
        view.startLoading();
        interactor.requestBengkel(new RequestCallback<List<Bengkel>>() {
            @Override
            public void requestSuccess(List<Bengkel> data) {
                view.endLoading();
                view.setBengkel(data);
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
        interactor.searchBengkel(keyword, new RequestCallback<List<Bengkel>>() {
            @Override
            public void requestSuccess(List<Bengkel> data) {
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
