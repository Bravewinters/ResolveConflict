package com.example.resolveconflict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resolveconflict.R;

public class TextRecycleAdapter extends RecyclerView.Adapter<TextRecycleAdapter.ViewHolder> {
    private LayoutInflater inflater;
    public TextRecycleAdapter(Context ctx) {
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public TextRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.app_recycle_item_text, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull TextRecycleAdapter.ViewHolder holder, int position) {
        holder.tvContent.setText("20210120");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
