package com.example.setupfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText email,password;
    String emails,passwords;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Getting Instance of FirebaseAuth
        mAuth=FirebaseAuth.getInstance();
        //Finding EditTexts
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Log.i("hello",currentUser.toString());
//
//    }

    public void newEntry(View view){
        emails=email.getText().toString();
        passwords=password.getText().toString();
        mAuth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("msg","successfuly signed in");
                    FirebaseUser user = mAuth.getCurrentUser();
                }
                else{
                    Log.w("msg","Unable to sign up");
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
