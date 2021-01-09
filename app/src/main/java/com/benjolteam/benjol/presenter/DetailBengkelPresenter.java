package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.DetailBengkelContract;
import com.benjolteam.benjol.interactor.DetailBengkelInteractor;
import com.benjolteam.benjol.model.Bengkel;

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
