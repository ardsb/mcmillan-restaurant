package com.example.mcmillanrestaurant.Adapter;

import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FoodAdapterManager extends RecyclerView.Adapter<FoodAdapterManager.ViewHolder>{
    private List<Food> dataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder   {
        private TextView name, description,price;
        private Button delete;
        private LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txtFoodName);
            description = view.findViewById(R.id.txtFoodDescription);
            price = view.findViewById(R.id.txtFoodPrice);
            delete=view.findViewById(R.id.btnDelete);
            layout = view.findViewById(R.id.layoutFood);

        }
    }

    public FoodAdapterManager(Context context, List<Food> dataSet) {
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


//        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Food name : " + dataSet.get(position).getName() ,Toast.LENGTH_LONG).show();
//
////                Intent track = new Intent(context, TrackActivity.class);
////                track.putExtra("selected",dataSet.get(position));
//////
////                context.startActivity(track);
//
//
//            }
//        });
//


        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "One record deleted ", Toast.LENGTH_SHORT).show();
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Dishes").child(dataSet.get(position).getId());
//
                dR.removeValue();
            }
        });


    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
