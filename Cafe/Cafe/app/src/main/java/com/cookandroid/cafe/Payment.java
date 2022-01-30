package com.cookandroid.cafe;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends Activity {

    RadioGroup rgpay,rgcard;
    RadioButton takeout,delivery,shinhan,nh,kb,samsung;
    Button btnPay,btnHome,btnSum;
    EditText address,phoneN;
    View dialogView;
    TextView totalPrice,Total,textPrice;


    public static Context context_coffee; // context 변수 선언
    public static Context context_beverage; // context 변수 선언
    public static Context context_dessert; // context 변수 선언

    int coffeeTotal=0;
    int beverageTotal = 0;
    int dessertTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        rgpay=findViewById(R.id.rgpay);
        rgcard=findViewById(R.id.rgcard);

        takeout=findViewById(R.id.takeout);
        delivery=findViewById(R.id.delivery);

        shinhan=findViewById(R.id.shinhan);
        nh=findViewById(R.id.nh);
        kb=findViewById(R.id.kb);
        samsung=findViewById(R.id.samsung);

        address = findViewById(R.id.address);
        phoneN=findViewById(R.id.phoneN);

        btnPay=findViewById(R.id.btnPay);
        btnHome=findViewById(R.id.btnHome);
        btnSum=findViewById(R.id.btnSum);

        Total=findViewById(R.id.Total);

        totalPrice = findViewById(R.id.totalPrice);

        context_coffee=this;    // onCreate에서 this 할당
        textPrice = findViewById(R.id.coffeePrice);


        context_dessert=this;    // onCreate에서 this 할당
        textPrice = findViewById(R.id.dessertPrice);

        context_beverage=this;    // onCreate에서 this 할당
        textPrice = findViewById(R.id.beveragePrice);


        // 총액 계산 후, DB에 넣기
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Total.setText("커피 총 액 : 0 원" +
                        "\n음료 총 액 : 0 원"+
                        "\n디저트 총 액 : 0 원");
                try {
                    Integer beverage_cost_s = ((Beverage)Beverage.context_beverage).beveragePrice;
                    Integer coffee_cost_s = ((Coffee)Coffee.context_coffee).coffeePrice;
                    Integer dessert_cost_s = ((Dessert)Dessert.context_dessert).dessertPrice;

                    Integer total  = beverage_cost_s+coffee_cost_s+dessert_cost_s;
                    String total_s= Integer.toString(total);
                    totalPrice.setText(total_s+"원");

                    Total.setText("커피 총 액 : "+((Coffee)Coffee.context_coffee).priceToString +
                            "\n음료 총 액 : "+((Beverage)Beverage.context_beverage).priceToString +
                            "\n디저트 총 액 : "+((Dessert)Dessert.context_dessert).priceToString);

                }catch (Exception ex){
                }
            }
        });

        //배달 클릭시, 배달주문 적는 칸 나오도록
        rgpay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String result;
                if(checkedId == R.id.delivery){
                    address.setVisibility(View.VISIBLE);
                }else{
                    address.setVisibility(View.INVISIBLE);
                }
            }
        });


        //결제 버튼 클릭 시
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //address 부분 DB에 들어가게
                //만약 기존에 데이터가 있으면 수정되도록

                dialogView =View.inflate(Payment.this,R.layout.paydialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(Payment.this);

                shinhan=dialogView.findViewById(R.id.shinhan);
                nh=dialogView.findViewById(R.id.nh);
                kb=dialogView.findViewById(R.id.kb);
                samsung=dialogView.findViewById(R.id.samsung);

                dlg.setTitle("결제 창");
                dlg.setView(dialogView);
                dlg.setIcon(R.drawable.pay);

                //취소 벝버튼 클릭 시
                dlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Payment.this,"결제를 취소하셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });

                // 확인 버튼 클릭 시
                dlg.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Payment.this,"결제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        address.setText("");        // 결제 후, 주소적힌 것 리셋
                        phoneN.setText("");         // 결제 후, 전화번호 적힌 것 리셋
                        totalPrice.setText("");     //결제 후, 총액 리셋


                        // 각 주문 값 0으로 리셋
                        ((Beverage)Beverage.context_beverage).beveragePrice=0;
                        ((Beverage)Beverage.context_beverage).textPrice.setText("0");

                        ((Coffee)Coffee.context_coffee).coffeePrice=0;
                        ((Coffee)Coffee.context_coffee).textPrice.setText("0");

                        ((Dessert)Dessert.context_dessert).dessertPrice=0;
                        ((Dessert)Dessert.context_dessert).textPrice.setText("0");

                        Total.setText("커피 총 액 : "+((Coffee)Coffee.context_coffee).coffeePrice +
                                "\n음료 총 액 : "+((Beverage)Beverage.context_beverage).beveragePrice +
                                "\n디저트 총 액 : "+((Dessert)Dessert.context_dessert).dessertPrice);


                    }
                });

                dlg.show();
            }
        });

        //뒤로 버튼 클릭 시
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Beverage)Beverage.context_beverage).beveragePrice=0;
                ((Coffee)Coffee.context_coffee).coffeePrice=0;
                ((Dessert)Dessert.context_dessert).dessertPrice=0;

                // 홈화면 누르고 들어오면 결제창 조금 문제. 해결.


                finish();
            }
        });


    }

    // 전화번호, 주소

}