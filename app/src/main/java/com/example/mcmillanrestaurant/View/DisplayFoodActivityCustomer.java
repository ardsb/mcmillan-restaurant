package com.example.mcmillanrestaurant.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mcmillanrestaurant.Adapter.FoodAdapterCustomer;
import com.example.mcmillanrestaurant.Model.Food;
import com.example.mcmillanrestaurant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayFoodActivityCustomer extends AppCompatActivity {

    RecyclerView recyclerFoods;
    List<Food> foods;
    Button delete, update;
    DatabaseReference myRef;
    String TAG = AddFoodActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_food);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Dishes");

        recyclerFoods=findViewById(R.id.reyclerFood);
        foods = new ArrayList<>();

        myRef = database.getReference("Dishes");//Database name




    }
    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foods.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Food food = postSnapshot.getValue(Food.class);

                    foods.add(food);
                }

                if (foods.size() > 0) {

                    FoodAdapterCustomer foodAdapterCustomer = new FoodAdapterCustomer(DisplayFoodActivityCustomer.this, foods);

                    FoodAdapterCustomer adapter = new FoodAdapterCustomer(DisplayFoodActivityCustomer.this, foods);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DisplayFoodActivityCustomer.this,2);
                    recyclerFoods.setLayoutManager(mLayoutManager);
                    recyclerFoods.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}