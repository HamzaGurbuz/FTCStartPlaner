package com.example.ftcstartplaner;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.StrategyViewHolder> {

    private ArrayList<Strategy> strategies;
    TextView tvName, tvDescription;
    ImageView ivDrawing, ivDelete; // <-- ivDelete burada tanımlanmalı

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
            holder.ivDrawing.setImageResource(R.drawable.field);
        }

        // ❗ Delete butonuna tıklanırsa detay sayfası açılmasın
        holder.ivDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Strategy")
                    .setMessage("Are you sure you want to delete this strategy?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Strategy toDelete = strategies.get(position);
                        StrategyDatabaseHelper dbHelper = new StrategyDatabaseHelper(v.getContext());
                        dbHelper.deleteStrategy(toDelete.name); // or use ID for safety
                        strategies.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(v.getContext(), "Strategy deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // 🎯 Tüm item'e tıklama (detay sayfası)
        holder.itemView.setOnClickListener(v -> {
            // Eğer tıklama sil butonundan geliyorsa işlemi yapma
            if (v.getId() == R.id.ivDelete) return;

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
        ImageView ivDrawing, ivDelete;

        public StrategyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStrategyName);
            tvDescription = itemView.findViewById(R.id.tvStrategyDesc);
            ivDrawing = itemView.findViewById(R.id.ivStrategyDrawing);
            ivDelete = itemView.findViewById(R.id.ivDelete); // <-- Silme butonunu burada bağla
        }
    }
}
