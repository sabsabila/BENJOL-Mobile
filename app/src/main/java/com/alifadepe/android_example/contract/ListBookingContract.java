package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.model.BookingData;

import java.util.List;

public interface ListBookingContract {
    interface View {
        void showError(String message);
        void setBooking(List<BookingData> bookings);
        void startLoading();
        void endLoading();
    }

    interface Presenter {
        void setBooking();
    }

    interface Interactor {
        void requestBookings(final RequestCallback<List<BookingData>> requestCallback);
    }
}
