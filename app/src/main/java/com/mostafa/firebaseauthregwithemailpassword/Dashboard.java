package com.mostafa.firebaseauthregwithemailpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private TextView tEmail,user_Uid;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _InIt();
        logOut();


    }

    private void logOut() {
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Dashboard.this,MainActivity.class);
            startActivity(intent);

        });
    }
    private  void _InIt(){
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tEmail = (TextView) findViewById(R.id.txt_Dashboard_email);
        user_Uid = (TextView) findViewById(R.id.txt_userUid);
        btnLogout = findViewById(R.id.Logout_id);

        tEmail.setText(getIntent().getStringExtra("email").toString());
        user_Uid.setText(getIntent().getStringExtra("Uid").toString());
    }

}