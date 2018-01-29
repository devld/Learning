package me.devld.intentfilterdemo;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Action1Activity extends AppCompatActivity {

    private TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_action1);

        mTextView = findViewById(R.id.act1_text);
        Uri uri = getIntent().getData();

        String action = getIntent().getAction();

        PackageManager.MATCH_DEFAULT_ONLY

        mTextView.setText("action: " + action + "\n");

        if (uri != null) {
            mTextView.append("uri: " + uri.toString());
        }

    }
}
