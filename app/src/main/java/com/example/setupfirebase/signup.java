package com.example.setupfirebase;

import android.content.Intent;
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
    public String emails,passwords;
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
//                    Log.d("msg","Successfully Signed Up");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(),"Successfully Signed Up ",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Enter the details to continue",Toast.LENGTH_LONG).show();
                    toEnterInfo(user);
                }
                else{
                    Log.w("msg","Unable to sign up");
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void toSignIn(View view){
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }

    public void toEnterInfo(FirebaseUser user){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("user",user.getEmail());
        startActivity(intent);
    }

}
