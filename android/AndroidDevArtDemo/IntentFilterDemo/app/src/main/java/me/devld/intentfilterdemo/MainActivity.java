package me.devld.intentfilterdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent();

        intent.setAction("me.devld.intentfilterdemo.action1");
        intent.addCategory("me.devld.intentfilterdemo.category1");
        intent.addCategory("me.devld.intentfilterdemo.category2");
        intent.addCategory("me.devld.intentfilterdemo.category3");

        startActivity(intent);
    }
}
