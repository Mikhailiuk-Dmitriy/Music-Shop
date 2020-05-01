package com.dmitriy_mikhailiuk.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView orderText;
    String[] addresses = {"mds5@mail.ru"};
    String subject = "Заказ из Music shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderText = findViewById(R.id.orderText);

        Intent orderIntent = getIntent();
        String userName = orderIntent.getStringExtra("userName");
        String goodsName = orderIntent.getStringExtra("goodsName");
        int quantity = orderIntent.getIntExtra("quantity", 0);
        double price = orderIntent.getDoubleExtra("price", 0);
        double orderPrice = orderIntent.getDoubleExtra("orderPrice",0);

        emailText = "User Name: " + userName + "\n" + "Goods Name: " + goodsName + "\n" + "Quantity: " + quantity + "\n" + "Price: " + price + "\n" + "Order Price: " + orderPrice;

        orderText.setText(emailText);
    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");

        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
