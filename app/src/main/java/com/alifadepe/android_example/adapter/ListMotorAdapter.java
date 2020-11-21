package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
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

    public ListMotorAdapter(List<Motorcycle> motorcycles, LayoutInflater layoutInflater) {
        this.motorcycles = motorcycles;
        this.layoutInflater = layoutInflater;
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMotorcycleBinding binding;

        public ViewHolder(@NonNull ItemMotorcycleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
