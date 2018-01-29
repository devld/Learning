package me.devld.launchmodedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class StandardActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_standard;
    }
}
