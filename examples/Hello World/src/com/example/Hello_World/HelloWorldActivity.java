package com.example.Hello_World;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloWorldActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout content = new LinearLayout(this);
        TextView text =new TextView(this);
        text.setText("Hello World");
        content.addView(text);
        setContentView(content);
    }
}
