package com.example.setupfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView welcomeView=(TextView)findViewById(R.id.welcomeView);
        Bundle bundle=getIntent().getExtras();
        String userName=bundle.getString("userName");
        welcomeView.setText("Welcome "+userName);
    }

}
