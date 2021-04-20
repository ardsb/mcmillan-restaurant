package com.example.mcmillanrestaurant.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcmillanrestaurant.Model.Food;
import com.example.mcmillanrestaurant.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity {
    DatabaseReference myRef;
    TextView foodName,foodDescription,foodPrice;
    Button addFood,view;
    String TAG = AddFoodActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Dishes");//Database name
//        myRef.setValue("hello arkam");




        foodName=findViewById(R.id.input_FoodName);
        foodDescription=findViewById(R.id.input_FoodDescription);
        foodPrice=findViewById(R.id.input_FoodPrice);



        addFood=findViewById(R.id.btnAdd);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String foodname = foodName.getText().toString().trim();
                String fooddescription = foodDescription.getText().toString().trim();
                String foodprice = foodPrice.getText().toString().trim();


                if(!TextUtils.isEmpty(foodname) && !TextUtils.isEmpty(fooddescription) && !TextUtils.isEmpty(foodprice)  ){

                addFood(foodname,fooddescription,foodprice);


//                    addFood(new Food("001","arkam","sdd","sdad"));
                }else {

                    Toast.makeText(getApplicationContext(),"Artist Name cannot be empty",Toast.LENGTH_SHORT).show();
                }

            }
        });


        view=findViewById(R.id.btnView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddFoodActivity.this, DisplayFoodActivityManager.class));
            }
        });


        myRef = database.getReference("Dishes");//Database name
    }




    public void addFood(String Name, String Description, String Price){


        String id = myRef.push().getKey();
        Food food = new Food(id,Name,Description,Price);
        myRef.child(id).setValue(food);


        Toast.makeText(this,"One Dish Added",Toast.LENGTH_SHORT).show();
    }
}