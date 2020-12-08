package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.databinding.ItemBengkelBinding;
import com.alifadepe.android_example.databinding.ItemSparepartBinding;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.Sparepart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBengkelAdapter extends RecyclerView.Adapter<ListBengkelAdapter.ViewHolder> {
    private List<Bengkel> bengkel;
    private LayoutInflater layoutInflater;
    private final ListItemClickListener mOnClickListener;

    public ListBengkelAdapter(List<Bengkel> bengkel, LayoutInflater layoutInflater, ListBengkelAdapter.ListItemClickListener onClickListener) {
        this.bengkel = bengkel;
        this.layoutInflater = layoutInflater;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBengkelBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBengkel(bengkel.get(position));
        if(bengkel.get(position).getPhone_number() != null)
            holder.binding.tvNumber.setText("+" + bengkel.get(position).getPhone_number());

        if(bengkel.get(position).getProfile_picture() != null){
            holder.binding.bengkelPlaceholder.setBackground(null);
            Picasso.get()
                    .load(ApiConstant.BASE_URL + "/" + bengkel.get(position).getProfile_picture())
                    .into(holder.binding.bengkelPlaceholder);
        }
    }

    @Override
    public int getItemCount() {
        if(bengkel != null){
            return bengkel.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemBengkelBinding binding;

        public ViewHolder(@NonNull ItemBengkelBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = bengkel.get(getAdapterPosition()).getBengkel_id();
            mOnClickListener.onListItemClick(position);
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
}
