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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText email,password;
    String emails,passwords;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }



    public void Authenticate(View view){
        emails=email.getText().toString();
        passwords=password.getText().toString();
        mAuth.signInWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("msg", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Succesfully Signed IN as "+user.toString(),Toast.LENGTH_LONG).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("msg", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user){
        //Finding Name of person with this email

        final String emails=user.getEmail().substring(0,email.length()-4);

        String name;

        mDatabase= FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot users: dataSnapshot.getChildren()){

                    User user1=users.getValue(User.class);
                    Log.i("name",user1.userName);

                    if(user1.emailID.equals(emails)){
                        Intent intent = new Intent(getApplicationContext(),home.class);
                        intent.putExtra("userName",user1.userName);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
