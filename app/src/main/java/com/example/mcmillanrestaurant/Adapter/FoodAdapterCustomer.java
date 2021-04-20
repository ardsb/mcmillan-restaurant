package com.example.mcmillanrestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mcmillanrestaurant.Model.Food;
import com.example.mcmillanrestaurant.R;
import com.example.mcmillanrestaurant.View.CustomerFoodsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FoodAdapterCustomer extends RecyclerView.Adapter<FoodAdapterCustomer.ViewHolder>{
    private List<Food> dataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder   {
        private TextView name, description,price;
        private Button delete,edit;
        private LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txtFoodName);
            description = view.findViewById(R.id.txtFoodDescription);
            price = view.findViewById(R.id.txtFoodPrice);
            delete=view.findViewById(R.id.btnDelete);
            edit=view.findViewById(R.id.btnEdit);
            layout = view.findViewById(R.id.layoutFood);
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);

        }
    }

    public FoodAdapterCustomer(Context context, List<Food> dataSet) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_food_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        viewHolder.name.setText(dataSet.get(position).getName());
        viewHolder.description.setText(dataSet.get(position).getDescription());
        viewHolder.price.setText(dataSet.get(position).getPrice());


        viewHolder.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Food name : " + dataSet.get(position).getName() ,Toast.LENGTH_LONG).show();

                Intent track = new Intent(context, CustomerFoodsActivity.class);
                track.putExtra("selected",dataSet.get(position));
//
                context.startActivity(track);


            }
        });
//





    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
