package me.devld.launchmodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    private String TAG = "LaunchModeDemo-" + this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void startStandard(View view) {
        startActivity(new Intent(this, StandardActivity.class));
    }

    public void startSingleTop(View view) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void startSingleTask(View view) {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }

    public void startSingleTaskWithAffinity(View view) {
        startActivity(new Intent(this, SingleTaskWithAffinityActivity.class));
    }

    public void startSingleInstance(View view) {
        startActivity(new Intent(this, SingleInstanceActivity.class));
    }

    protected abstract int getLayoutId();

}
