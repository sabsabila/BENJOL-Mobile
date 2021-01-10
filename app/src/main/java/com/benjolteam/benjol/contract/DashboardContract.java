package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.BookingData;
import com.benjolteam.benjol.model.Profile;

public interface DashboardContract {
    interface View {
        void setUser(Profile user);
        void setBooking(BookingData booking);
        void showError(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void getFullname();
        void getBookingData();
    }

    interface Interactor {
        void getUser(RequestCallback<Profile> requestCallback);
        void getLatestBooking(RequestCallback<BookingData> requestCallback);
    }
}
