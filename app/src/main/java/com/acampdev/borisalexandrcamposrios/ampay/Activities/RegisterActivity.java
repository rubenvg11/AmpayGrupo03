package com.acampdev.borisalexandrcamposrios.ampay.Activities;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    EditText email,password, repassword;
    Button registrarse, inicioSesion;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // iniciamos Firebase Auth
        auth= FirebaseAuth.getInstance();


        email=(EditText) findViewById(R.id.emailRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        repassword=(EditText) findViewById(R.id.repasswordRegistrer);

        registrarse = (Button) findViewById(R.id.registerAccount);
        inicioSesion= (Button) findViewById(R.id.loginSesion);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail= email.getText().toString();
                String pass= password.getText().toString();
                String repass= repassword.getText().toString();

                // validamos se puede hacer con equals tambien distintas formas

                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Colocar un Email",Toast.LENGTH_LONG).show();
                }else {
                    if(TextUtils.isEmpty(pass)){
                        Toast.makeText(getApplicationContext(),"Colocar un Password",Toast.LENGTH_LONG).show();
                    }else {
                        if(pass.equals(repass)){
                            auth.createUserWithEmailAndPassword(mail,repass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(getApplicationContext(),"Se a creado el Usuario",Toast.LENGTH_SHORT).show();
                                    if(!task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Problema para crear usuario",Toast.LENGTH_SHORT).show();
                                    }
                                    Intent intent= new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),"Los Password no coinciden",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
