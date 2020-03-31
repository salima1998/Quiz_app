package com.example.cm.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrerActivity extends AppCompatActivity {

    TextView tvlog;
    EditText mName, mEmail, mLogin, mPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
        tvlog = findViewById(R.id.tvlog);

        mName = findViewById(R.id.etName);
        mEmail = findViewById(R.id.Mail);
        mLogin = findViewById(R.id.Login);
        mPassword = findViewById(R.id.etPassword);
        mRegisterBtn = findViewById(R.id.bRegister);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            private static final String TAG = "";

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                    Toast.makeText(RegistrerActivity.this,"Logged in Successfully ",Toast.LENGTH_SHORT).show();


                                }
                                else{
                                    Toast.makeText(RegistrerActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        tvlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //traitement
                startActivity(new Intent(RegistrerActivity.this, LoginActivity.class));
            }
        });

    }
}


