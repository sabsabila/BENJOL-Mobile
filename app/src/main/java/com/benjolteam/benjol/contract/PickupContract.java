package com.benjolteam.benjol.contract;

import java.util.ArrayList;

public interface PickupContract {
    interface View {
        void startLoading();
        void endLoading();
        void sendLocation(ArrayList<String> location);
    }

    interface Presenter {
        void inputLocation(ArrayList<String> location);
    }
}
