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

public class Beverage extends Activity implements View.OnClickListener {

    private ImageButton beverageImgButton[] = new ImageButton[12];
    private Integer[] beverageButton = {
            R.id.beverage0, R.id.beverage1, R.id.beverage2, R.id.beverage3, R.id.beverage4, R.id.beverage5,
            R.id.beverage6, R.id.beverage7, R.id.beverage8, R.id.beverage9, R.id.beverage10, R.id.beverage11
    };
    private String[] beverageMenu = {
            "청포도 에이드", "자몽 에이드", "복숭아 에이드", "초코 라떼", "녹차 라떼","고구마 라떼",
            "아이스 티", "망고 스무디", "딸기 스무디", "망고 요거트", "딸기 요거트", "플레인 요거트"
    };
    int[] price={5000, 5000, 5000, 5000, 5000, 5000, 2500, 5000, 5000, 5000, 5000, 5000};

    public int beveragePrice=0;
    public static Context context_beverage; // context 변수 선언
    String priceToString="0";
    ArrayList<String> DataList1;

    TextView textPrice;
    Button btnNoBeverage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beverage);

        context_beverage=this;  // onCreate에서 this 할당

        textPrice = findViewById(R.id.beveragePrice);
        btnNoBeverage=findViewById(R.id.btnNoBeverage);

        DataList1 = new ArrayList<String>();

        for(int i=0; i<beverageImgButton.length; i++) beverageImgButton[i] = findViewById(beverageButton[i]);

        for(int i=0; i<beverageImgButton.length; i++){
            beverageImgButton[i].setTag(i);
            beverageImgButton[i].setOnClickListener(this);
            DataList1.add(beverageMenu[i] + "를 주문하셨습니다.");

        }



        btnNoBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceToString ="0";
                beveragePrice = 0;
                Toast.makeText(Beverage.this,"음료 주문하지 않기!",Toast.LENGTH_SHORT).show();
                priceToString = Integer.toString(beveragePrice);
                textPrice.setText(priceToString);
            }
        });
    }

    @Override
    public void onClick(View view) {
        ImageButton newButton = (ImageButton) view;
        for(ImageButton tempButton : beverageImgButton){
            if(tempButton == newButton){
                int position = (Integer) view.getTag();
                Toast.makeText(this, DataList1.get(position), Toast.LENGTH_SHORT).show();
                beveragePrice = beveragePrice + price[position];
                priceToString = Integer.toString(beveragePrice);
                textPrice.setText(priceToString);
            }
        }

    }





}
