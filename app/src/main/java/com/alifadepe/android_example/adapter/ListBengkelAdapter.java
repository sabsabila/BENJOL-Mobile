package com.alifadepe.android_example.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alifadepe.android_example.databinding.ItemBengkelBinding;
import com.alifadepe.android_example.databinding.ItemSparepartBinding;
import com.alifadepe.android_example.model.Bengkel;
import com.alifadepe.android_example.model.Sparepart;

import java.util.List;

public class ListBengkelAdapter extends RecyclerView.Adapter<ListBengkelAdapter.ViewHolder> {
    private List<Bengkel> bengkel;
    private LayoutInflater layoutInflater;

    public ListBengkelAdapter(List<Bengkel> bengkel, LayoutInflater layoutInflater) {
        this.bengkel = bengkel;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBengkelBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBengkel(bengkel.get(position));
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemBengkelBinding binding;

        public ViewHolder(@NonNull ItemBengkelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
