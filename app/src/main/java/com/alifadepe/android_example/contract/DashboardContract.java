package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.BookingData;
import com.alifadepe.android_example.model.Profile;

public interface DashboardContract {
    interface View {
        void setUser(Profile user);
        void setBooking(BookingData booking);
        void showError(String message);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void getUsername();
        void getBookingData();
    }

    interface Interactor {
        void getUser(RequestCallback<Profile> requestCallback);
        void getLatestBooking(RequestCallback<BookingData> requestCallback);
    }
}
