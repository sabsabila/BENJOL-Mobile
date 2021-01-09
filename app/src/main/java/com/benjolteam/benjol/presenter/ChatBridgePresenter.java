package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.contract.ChatBridgeContract;
import com.benjolteam.benjol.interactor.ChatBridgeInteractor;
import com.benjolteam.benjol.model.Bengkel;

import java.util.List;

public class ChatBridgePresenter implements ChatBridgeContract.presenter {
    private ChatBridgeContract.View view;
    private ChatBridgeInteractor interactor;

    public ChatBridgePresenter(ChatBridgeContract.View view, ChatBridgeInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setBengkel() {
        view.startLoading();
        interactor.requestBengkel(new RequestCallback<List<Bengkel>>() {
            @Override
            public void requestSuccess(List<Bengkel> data) {
                view.setBengkel(data.get(0));
                view.endLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showError(errorMessage);
                view.endLoading();
            }
        });
    }

}
