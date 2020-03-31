package com.example.cm.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {
    EditText etLogin,etPassword;
    Button bLogin;
    TextView tvregister;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);etLogin= (EditText)findViewById(R.id.etLogin);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bLogin=(Button)findViewById(R.id.blogin);
        tvregister=findViewById(R.id.tvRegister);
        fauth= FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar2);



        //Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email=etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    etLogin.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    etPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    etPassword.setError("Password Must Be  >=6 Characters");
                    return;
                }

                //authenticate the user
                fauth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Logged in Successfully ",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this,Quiz1.class));

                                }
                                else{
                                    Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrerActivity.class));
            }
        });


    }
}
