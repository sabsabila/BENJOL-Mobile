package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.DetailBengkelContract;
import com.alifadepe.android_example.interactor.DetailBengkelInteractor;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.Profile;

import java.util.List;

public class DetailBengkelPresenter implements DetailBengkelContract.Presenter {
    private DetailBengkelContract.View view;
    private DetailBengkelInteractor interactor;

    public DetailBengkelPresenter(DetailBengkelContract.View view, DetailBengkelInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setBengkel() {
        view.startLoading();
        interactor.requestBengkel(new RequestCallback<List<Bengkel>>() {
            @Override
            public void requestSuccess(List<Bengkel> bengkel) {
                view.setBengkel(bengkel.get(0));
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }

    @Override
    public void bookService() {
        view.redirectToBooking();
    }

    @Override
    public void searchSparepart() {
        view.redirectToListSparepart();
    }
}
