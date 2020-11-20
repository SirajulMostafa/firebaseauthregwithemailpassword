package com.mostafa.firebaseauthregwithemailpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final static String TAG="abc";
    private TextView tEmail,tPassword;
    private ProgressBar progressBar;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerUser();
    }

    private void registerUser() {
        signUp = (Button) findViewById(R.id.btnSingUp);
        tEmail = (TextView) findViewById(R.id.txtEmail);
        tPassword =  (TextView) findViewById(R.id.txtPassword);
        progressBar =   (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   progressBar.setVisibility(View.VISIBLE);

                final     String email = tEmail.getText().toString();
                final    String password = tPassword.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            progressBar.setVisibility(View.INVISIBLE);
                            tEmail.setText("");
                            tPassword.setText("");
                            Toast.makeText(MainActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
            }
        });



    }

}