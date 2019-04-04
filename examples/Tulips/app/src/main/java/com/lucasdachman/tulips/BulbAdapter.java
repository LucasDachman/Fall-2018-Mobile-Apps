package com.lucasdachman.tulips;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BulbAdapter extends RecyclerView.Adapter<BulbAdapter.ViewHolder> {

    ListItemClickListener itemClickListener;
    public BulbAdapter(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BulbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View bulbView = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(bulbView);
    }

    @Override
    public void onBindViewHolder(@NonNull BulbAdapter.ViewHolder viewHolder, int i) {
       viewHolder.bulbTextView.setText(Bulb.tulips.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return Bulb.tulips.size();
    }

    public interface ListItemClickListener {
        void onClick(int position);
    }


     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bulbTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bulbTextView = itemView.findViewById(R.id.textView);
            bulbTextView.setOnClickListener(this);
        }


         @Override
         public void onClick(View v) {
            itemClickListener.onClick(getAdapterPosition());
         }
     }
}
