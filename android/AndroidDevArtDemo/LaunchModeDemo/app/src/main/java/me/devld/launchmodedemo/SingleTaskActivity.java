package me.devld.launchmodedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class SingleTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_single_task;
    }
}
