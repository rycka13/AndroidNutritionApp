package com.rycka13.nutritionapp.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.data.Weight;

import java.util.ArrayList;

public class WeightListAdapter extends RecyclerView.Adapter<WeightListAdapter.ViewHolder> {

    private ArrayList<Weight> weights;
    private OnClickListener onClick;

    public WeightListAdapter(ArrayList<Weight> weights){
        this.weights = weights;
    }

    public void setOnClick(OnClickListener listener){
        this.onClick = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weight_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(weights.get(position).getDateString());
        holder.weight.setText(weights.get(position).getWeight().toString());
    }

    @Override
    public int getItemCount() {
        return weights.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView weight;

        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.listDate);
            weight = itemView.findViewById(R.id.listWeight);
            itemView.setOnClickListener(v -> {
                onClick.onClick(weights.get(getBindingAdapterPosition()));
            });
        }

    }
    public interface OnClickListener {
        void onClick(Weight weight);
        }

}
