package com.cookandroid.cafe;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Main extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //첫 번째 탭
        TabHost tabhost = getTabHost();
        TabHost.TabSpec spec;

        Intent intent = new Intent().setClass(this, Coffee.class);
        spec = tabhost.newTabSpec("Coffee").setIndicator("커피").setContent(intent);
        tabhost.addTab(spec);

        //두 번째 탭
        intent = new Intent().setClass(this, Beverage.class);
        spec = tabhost.newTabSpec("Beverage").setIndicator("음료").setContent(intent);
        tabhost.addTab(spec);

        //세 번째 탭
        intent = new Intent().setClass(this, Dessert.class);
        spec = tabhost.newTabSpec("Dessert.").setIndicator("디저트").setContent(intent);
        tabhost.addTab(spec);

        // 네 번쨰 탭
        intent = new Intent().setClass(this, Payment.class);
        spec = tabhost.newTabSpec("Payment").setIndicator("결제").setContent(intent);
        tabhost.addTab(spec);

        //처음 앱 실행시 탭 활성화 지정
        tabhost.setCurrentTab(0);
    }
}
