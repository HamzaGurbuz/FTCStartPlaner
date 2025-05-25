package com.example.ftcstartplaner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.StrategyViewHolder> {

    private ArrayList<Strategy> strategies;

    public StrategyAdapter(ArrayList<Strategy> strategies) {
        this.strategies = strategies;
    }

    @NonNull
    @Override
    public StrategyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_strategy, parent, false);
        return new StrategyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StrategyViewHolder holder, int position) {
        Strategy strategy = strategies.get(position);

        holder.tvName.setText(strategy.name);
        holder.tvDescription.setText(strategy.description);

        Bitmap bitmap = BitmapFactory.decodeFile(strategy.drawingPath);
        if (bitmap != null) {
            holder.ivDrawing.setImageBitmap(bitmap);
        } else {
            holder.ivDrawing.setImageResource(R.drawable.field); // Eğer çizim yoksa gösterilecek placeholder
        }

        // Tıklayınca detay sayfasına geçiş
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StrategyDetailActivity.class);
            intent.putExtra("strategy", strategy);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return strategies.size();
    }

    public static class StrategyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription;
        ImageView ivDrawing;

        public StrategyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStrategyName);
            tvDescription = itemView.findViewById(R.id.tvStrategyDesc);
            ivDrawing = itemView.findViewById(R.id.ivStrategyDrawing);
        }
    }
}
