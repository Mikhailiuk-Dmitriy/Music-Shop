package com.dmitriy_mikhailiuk.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 1;
    TextView quantityText;
    Button minusButton, plusButton;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityText = findViewById(R.id.quantityText);
        minusButton = findViewById(R.id.minusButton);
        plusButton = findViewById(R.id.plusButton);
        userName = findViewById(R.id.userName);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Скрипка");
        spinnerArrayList.add("Гитара");
        spinnerArrayList.add("Барабан");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        goodsMap = new HashMap();
        goodsMap.put("Скрипка", 500.0);
        goodsMap.put("Гитара", 1000.0);
        goodsMap.put("Барабан", 1500.0);
    }

    public void minus(View view) {
        if (quantity > 1){
            quantity --;
            quantityText.setText(String.valueOf(quantity));
            TextView priceText = findViewById(R.id.price);
            priceText.setText("" + quantity * price);
        }else {
            quantity = 1;
            quantityText.setText(String.valueOf(quantity));
        }
    }

    public void plus(View view) {
        quantity ++;
        quantityText.setText(String.valueOf(quantity));
        TextView priceText = findViewById(R.id.price);
        priceText.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceText = findViewById(R.id.price);
        priceText.setText("" + quantity * price);

        ImageView goodsImage = findViewById(R.id.goodsImage);

        switch (goodsName){
            case "Скрипка":
                goodsImage.setImageResource(R.drawable.violin);
                break;
            case "Гитара":
                goodsImage.setImageResource(R.drawable.guitar);
                break;
            case "Барабан":
                goodsImage.setImageResource(R.drawable.drums);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();
        order.userName = userName.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);
    }
}
