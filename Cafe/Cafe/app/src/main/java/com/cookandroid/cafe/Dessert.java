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

public class Dessert extends Activity implements View.OnClickListener {
    ImageButton dessertImgButton[] = new ImageButton[12];
    Integer[] dessertButton = {
            R.id.dessert0, R.id.dessert1, R.id.dessert2, R.id.dessert3, R.id.dessert4, R.id.dessert5,
            R.id.dessert6, R.id.dessert7, R.id.dessert8, R.id.dessert9, R.id.dessert10, R.id.dessert11
    };
    String[] dessertMenu = {
            "애플시나몬 와플", "바나나 와플", "우유카라멜 와플", "오레오 와플", "딸기 와플", "녹차 롤케이크",
            "초코 롤케이크", "우유 롤케이크", "치즈 케이크", "오레오 케이크", "초코 케이크", "티라미수 케이크"
};
    int[] price={3000, 3000, 3000, 3500, 3500, 5000, 5000, 5000, 6500, 6500, 6500, 6500};

public static Context context_dessert;  // context 변수 선언
        String priceToString="0";

        int dessertPrice=0;
    ArrayList<String> DataList1;

    TextView textPrice;
    Button btnNoDessert;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessert);

        context_dessert = this; // onCreate에서 this 할당

        textPrice = findViewById(R.id.dessertPrice);
        btnNoDessert=findViewById(R.id.btnNoDessert);

        DataList1 = new ArrayList<String>();

        for(int i=0; i<dessertImgButton.length; i++) dessertImgButton[i] = findViewById(dessertButton[i]);
        for(int i=0; i<dessertImgButton.length; i++){
            dessertImgButton[i].setTag(i);
            dessertImgButton[i].setOnClickListener(Dessert.this);
            DataList1.add(dessertMenu[i] + "를 주문하셨습니다.");

        }



        btnNoDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceToString ="0";
                dessertPrice = 0;
                priceToString = Integer.toString(dessertPrice);
                textPrice.setText(priceToString);
                Toast.makeText(Dessert.this,"디저트 주문하지 않기!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        ImageButton newButton = (ImageButton) view;

        for(ImageButton tempButton : dessertImgButton){
            if(tempButton == newButton){
                int position = (Integer) view.getTag();
                Toast.makeText(this, DataList1.get(position), Toast.LENGTH_SHORT).show();
                dessertPrice = dessertPrice + price[position];
                priceToString = Integer.toString(dessertPrice);
                textPrice.setText(priceToString);
            }
        }


    }


}
