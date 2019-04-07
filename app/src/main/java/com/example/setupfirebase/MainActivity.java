package com.example.setupfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText Name, PhNum, Email;
    String name, pnum, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Finding Edit Texts
        Name = (EditText) findViewById(R.id.name);
        PhNum = (EditText) findViewById(R.id.pNum);
        Email = (EditText) findViewById(R.id.email);
        //Instantiating and getting refrence to database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public void createEntry(View view) {
        //Accessing Strings Entered by user in the EditTexts
        name = Name.getText().toString();
        pnum = PhNum.getText().toString();
        email = Email.getText().toString();
        email = email.substring(0,email.length()-4);
        //Intantiating User Object
        User user = new User(name, email, pnum);
        //Entering to the Databse
        try {
            myRef.child("users").child(email).setValue(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}