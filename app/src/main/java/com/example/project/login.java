package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
    public Button btnRegister;

    private EditText editTextEmail,editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn=(Button)findViewById(R.id.signin);
        signIn.setOnClickListener(this);
        editTextEmail=(EditText)findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();


        btnRegister=(Button)findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(login.this,Register.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String email = editTextEmail. getText( ) . toString() . trim();
        String password = editTextPassword. getText( ) . toString() . trim( );
        if(email. isEmpty ( ) ) {

            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns. EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;

        }
        if(password.length()<6){
            editTextPassword.setError("password should be 6 character or more");
            editTextPassword.requestFocus();

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(login.this,MainActivity.class));

                }
                else{
                    Toast.makeText(login.this, "Failed to login! please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}