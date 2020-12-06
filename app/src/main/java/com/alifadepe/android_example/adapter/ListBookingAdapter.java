package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.databinding.ItemBengkelBinding;
import com.alifadepe.android_example.databinding.ItemBookingBinding;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.BookingData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBookingAdapter extends RecyclerView.Adapter<ListBookingAdapter.ViewHolder> {
    private List<BookingData> bookings;
    private LayoutInflater layoutInflater;
    private final ListItemClickListener mOnClickListener;

    public ListBookingAdapter(List<BookingData> bookings, LayoutInflater layoutInflater, ListBookingAdapter.ListItemClickListener onClickListener) {
        this.bookings = bookings;
        this.layoutInflater = layoutInflater;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookingBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBooking(bookings.get(position));
    }

    @Override
    public int getItemCount() {
        if(bookings != null){
            return bookings.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemBookingBinding binding;

        public ViewHolder(@NonNull ItemBookingBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = bookings.get(getAdapterPosition()).getBooking_id();
            mOnClickListener.onListItemClick(position);
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
}
