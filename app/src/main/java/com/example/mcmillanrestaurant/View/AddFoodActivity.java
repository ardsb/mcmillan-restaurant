package com.example.mcmillanrestaurant.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcmillanrestaurant.Adapter.FoodAdapter;
import com.example.mcmillanrestaurant.Model.Food;
import com.example.mcmillanrestaurant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {
    DatabaseReference myRef;
    RecyclerView recyclerFoods;
    List<Food> foods;
    TextView foodName,foodDescription,foodPrice;
    Button addFood;
    Spinner spinner;
    String TAG = AddFoodActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Studio");//Database name

        recyclerFoods=findViewById(R.id.recyclerFood);
        foods = new ArrayList<>();

        foodName=findViewById(R.id.input_FoodName);
        foodDescription=findViewById(R.id.input_FoodDescription);
        foodPrice=findViewById(R.id.input_FoodPrice);

        spinner=findViewById(R.id.spinner);
        setSpinner();

        addFood=findViewById(R.id.btnAdd);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String foodname = foodName.getText().toString().trim();
                String fooddescription = foodDescription.getText().toString().trim();
                String foodprice = foodPrice.getText().toString().trim();


                if(!TextUtils.isEmpty(foodname) && !TextUtils.isEmpty(fooddescription) && !TextUtils.isEmpty(foodprice)  ){
                addFood(foodname,fooddescription,foodprice,spinner.getSelectedItem().toString());

//                    addFood(new Food("001","arkam","sdd","sdad"));
                }else {

                    Toast.makeText(getApplicationContext(),"Artist Name cannot be empty",Toast.LENGTH_SHORT).show();
                }

            }
        });

        myRef = database.getReference("Studio");//Database name
    }

    public void setSpinner(){
        List<String> categories = new ArrayList<String>();
        categories.add("100g");
        categories.add("250g");
        categories.add("500g");
        categories.add("750g");
        categories.add("1kg");
        categories.add("2kg");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foods.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Food food = postSnapshot.getValue(Food.class);
                    //adding artist to the list
                    foods.add(food);
                }
                //Validate whether there artist
                if(foods.size()>0){
                    //creating adapter
                    FoodAdapter foodAdapter = new FoodAdapter(AddFoodActivity.this, foods);
                    //Setting up data in recycler
                    FoodAdapter adapter = new FoodAdapter(AddFoodActivity.this, foods);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AddFoodActivity.this);
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
    //    Adding the artist
    public void addFood(String foodname, String Name, String description, String Price){
// Creating a unique identifier
// A primary key

        String id = myRef.push().getKey();
//  Creating artist model
        Food food = new Food(id,Name,description,Price);
//   Saving the Artist
        myRef.child(id).setValue(food);


        Toast.makeText(this,"One Artist Added",Toast.LENGTH_SHORT).show();
    }
}