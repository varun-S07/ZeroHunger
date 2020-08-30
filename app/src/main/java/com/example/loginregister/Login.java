package com.example.loginregister;

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

public class Login extends AppCompatActivity {
    EditText email1,password;
    Button loginbtn;
    ProgressBar pbar;
    FirebaseAuth fauth;
    TextView newuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email1=findViewById(R.id.email);
        password=findViewById(R.id.password1);
        loginbtn=findViewById(R.id.Login);
        pbar=findViewById(R.id.progressBar2);
        newuser=findViewById(R.id.textView5);
        fauth=FirebaseAuth.getInstance();
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Password=password.getText().toString().trim();
                String Email=email1.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    email1.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                if (Password.length()<8){
                    password.setError("Passeord must be atleast 8 characters");
                    return;
                }
                pbar.setVisibility(View.VISIBLE);
                fauth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else{
                            Toast.makeText(Login.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pbar.setVisibility(View.INVISIBLE);


                        }
                    }
                });
            }
        });
    }
}