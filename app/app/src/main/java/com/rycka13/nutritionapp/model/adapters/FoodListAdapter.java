package com.rycka13.nutritionapp.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rycka13.nutritionapp.R;
import com.rycka13.nutritionapp.model.Model;
import com.rycka13.nutritionapp.model.ModelManager;
import com.rycka13.nutritionapp.model.data.Food;
import com.rycka13.nutritionapp.model.data.Weight;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private ArrayList<Food> foods;
    private OnClickListener onClick;
    private Model model;

    public FoodListAdapter(ArrayList<Food> foods){
        this.foods = foods;
    }

    public void setOnClick(OnClickListener listener){
        this.onClick = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        model = new ModelManager();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.foods_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(foods.get(position).getDate().toString());
        holder.foodName.setText(foods.get(position).getFoodName());
        holder.calories.setText(model.getFoodCalories(foods.get(position).getCaloriesPer100Grams(),foods.get(position).getGramsConsumed()).toString() + " calories");
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView date;
        TextView calories;


        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.foodDate);
            foodName = itemView.findViewById(R.id.foodName);
            calories = itemView.findViewById(R.id.calories);
            itemView.setOnClickListener(v -> {
                onClick.onClick(foods.get(getBindingAdapterPosition()));
            });
        }

    }
    public interface OnClickListener {
        void onClick(Food food);
        }

}
