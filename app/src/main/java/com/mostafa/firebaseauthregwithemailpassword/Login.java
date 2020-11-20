package com.mostafa.firebaseauthregwithemailpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final static String TAG="abc";
    private TextView tEmail,tPassword;
    private ProgressBar progressBar;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            userLogin();
    }

    private void userLogin() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tEmail = (TextView) findViewById(R.id.txtEmail_login);
        tPassword =  (TextView) findViewById(R.id.txtPassword_login);
        progressBar =   (ProgressBar) findViewById(R.id.progressBar_Login);
        progressBar.setVisibility(View.GONE);


        btnLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            final     String email = tEmail.getText().toString();
            final    String password = tPassword.getText().toString();

            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(Login.this,Dashboard.class);
                                intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                intent.putExtra("Uid",mAuth.getCurrentUser().getUid());
                                startActivity(intent);
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                tEmail.setText("");
                                tPassword.setText("");
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Email/Password incorrect.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

                });
            }


}