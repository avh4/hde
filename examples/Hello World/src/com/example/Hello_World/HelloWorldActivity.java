package com.example.Hello_World;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorldActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_activity);
    }
}
