package com.acampdev.borisalexandrcamposrios.ampay.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login, register;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();

        username=(EditText) findViewById(R.id.usernameApp);
        password=(EditText) findViewById(R.id.passwordApp);

        login=(Button) findViewById(R.id.loginButton);
        register=(Button) findViewById(R.id.registerButton);

/*
        stateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        };*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starSigIn();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);
    } */

    private  void starSigIn(){
        String user= username.getText().toString();
        String pass=password.getText().toString();
        if(TextUtils.isEmpty(user)){
            Toast.makeText(getApplicationContext(),"Ingrese un email",Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(),"Ingrese un password",Toast.LENGTH_SHORT).show();
            }else{
                auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "correo password incorrecto", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }
    }




}
