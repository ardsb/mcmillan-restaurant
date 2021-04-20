package com.example.mcmillanrestaurant.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mcmillanrestaurant.Model.Food;
import com.example.mcmillanrestaurant.R;

import java.util.ArrayList;

public class CustomerFoodsActivity extends AppCompatActivity {
    TextView dishName,dishDescription,dishPrice;
    ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_foods);

        foods=new ArrayList<>();

        Food selected = (Food) getIntent().getSerializableExtra("selected");
        dishName=findViewById(R.id.txtDishName);
        dishName.setText(selected.getName());

        dishDescription=findViewById(R.id.txtDishDescription);
        dishDescription.setText(selected.getDescription());

        dishPrice=findViewById(R.id.txtDishPrice);
        dishPrice.setText(selected.getPrice());



    }
}