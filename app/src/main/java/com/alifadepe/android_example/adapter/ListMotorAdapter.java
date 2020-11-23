package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.databinding.ItemBookBinding;
import com.alifadepe.android_example.databinding.ItemMotorcycleBinding;
import com.alifadepe.android_example.model.Book;
import com.alifadepe.android_example.model.Motorcycle;

import java.util.List;

public class ListMotorAdapter extends RecyclerView.Adapter<ListMotorAdapter.ViewHolder> {
    private List<Motorcycle> motorcycles;
    private LayoutInflater layoutInflater;
    private final ListItemClickListener mOnClickListener;
    private final ListItemLongClickListener mOnLongClickListener;

    public ListMotorAdapter(List<Motorcycle> motorcycles, LayoutInflater layoutInflater, ListItemClickListener onClickListener, ListItemLongClickListener onLongClickListener ) {
        this.motorcycles = motorcycles;
        this.layoutInflater = layoutInflater;
        this.mOnClickListener = onClickListener;
        this.mOnLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMotorcycleBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setMotorcycle(motorcycles.get(position));
    }

    @Override
    public int getItemCount() {
        if(motorcycles != null){
            return motorcycles.size();
        }
        else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ItemMotorcycleBinding binding;

        public ViewHolder(@NonNull ItemMotorcycleBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            this.binding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = motorcycles.get(getAdapterPosition()).getMotorcycle_id();
            mOnClickListener.onListItemClick(position);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = motorcycles.get(getAdapterPosition()).getMotorcycle_id();
            mOnLongClickListener.onListItemLongClick(position);
            return true;
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    public interface ListItemLongClickListener{
        boolean onListItemLongClick(int position);
    }
}
