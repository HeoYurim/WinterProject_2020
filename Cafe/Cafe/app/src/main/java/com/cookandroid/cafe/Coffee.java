package com.cookandroid.cafe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Coffee extends Activity implements View.OnClickListener{
    ImageButton coffeeImgButton[] = new ImageButton[10];
    Integer[] coffeeButton = {
            R.id.coffee0, R.id.coffee1, R.id.coffee2, R.id.coffee3, R.id.coffee4,
            R.id.coffee5, R.id.coffee6, R.id.coffee7, R.id.coffee8, R.id.coffee9
    };
    String[] coffeeMenu = {
            "아메리카노(ICE)", "아메리카노(HOT)", "카페 라떼(ICE)", "카페 라떼(HOT)", "카페 모카(ICE)",
            "카페 모카(HOT)", "카라멜 라떼(ICE)", "카라멜 라떼(HOT)", "바닐라 라떼(ICE)", "바닐라 라떼(HOT)"
    };
    int[] price={3000, 3000, 3500, 3500, 3500, 3500, 4000, 4000, 4000, 4000};


    public static Context context_coffee; // context 변수 선언
    public String priceToString="0";

    int coffeePrice=0;
    ArrayList<String> DataList1;
    Button btnNoCoffee;

    TextView textPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee);

        context_coffee=this;    // onCreate에서 this 할당

        textPrice = findViewById(R.id.coffeePrice);
        btnNoCoffee=findViewById(R.id.btnNoCoffee);


        DataList1 = new ArrayList<String>();

        for(int i=0; i<coffeeImgButton.length; i++) coffeeImgButton[i] = findViewById(coffeeButton[i]);
        for(int i=0; i<coffeeImgButton.length; i++){
            coffeeImgButton[i].setTag(i);
            coffeeImgButton[i].setOnClickListener(this);
            DataList1.add(coffeeMenu[i] + "를 주문하셨습니다.");

        }


        btnNoCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceToString ="0";
                coffeePrice = 0;
                priceToString = Integer.toString(coffeePrice);
                textPrice.setText(priceToString);
                Toast.makeText(Coffee.this,"커피 주문하지 않기!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        ImageButton newButton = (ImageButton) view;
        for(ImageButton tempButton : coffeeImgButton){
            if(tempButton == newButton){
                int position = (Integer) view.getTag();
                Toast.makeText(this, DataList1.get(position), Toast.LENGTH_SHORT).show();
                coffeePrice = coffeePrice + price[position];
                priceToString = Integer.toString(coffeePrice);
                textPrice.setText(priceToString);
            }
        }





    }


}
