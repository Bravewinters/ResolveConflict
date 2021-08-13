package com.example.resolveconflict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resolveconflict.R;

import java.util.ArrayList;
import java.util.List;

public class ImageVPAdapter extends RecyclerView.Adapter<ImageVPAdapter.ViewHolder> {
    private List<Integer> resourceIds = new ArrayList<>();
    private LayoutInflater inflater;
    public ImageVPAdapter(Context context) {
        resourceIds.add(R.mipmap.material1);
        resourceIds.add(R.mipmap.material2);
        resourceIds.add(R.mipmap.material3);
        resourceIds.add(R.mipmap.material4);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.app_pager_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageVPAdapter.ViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(resourceIds.get(position));
    }

    @Override
    public int getItemCount() {
        return resourceIds.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
