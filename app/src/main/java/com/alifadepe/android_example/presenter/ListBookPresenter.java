package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.ListBookContract;
import com.alifadepe.android_example.model.Book;

import java.util.List;

public class ListBookPresenter implements ListBookContract.Presenter {
    private ListBookContract.View view;
    private ListBookContract.Interactor interactor;

    public ListBookPresenter(ListBookContract.View view, ListBookContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestListBook() {
        view.startLoading();
        interactor.requestListBook(new RequestCallback<List<Book>>() {
            @Override
            public void requestSuccess(List<Book> data) {
                view.endLoading();
                view.showListBook(data);
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
