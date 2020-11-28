package com.example.foodbankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email, username, password;
        Button btn;
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pwd);
        btn = findViewById(R.id.signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Amplify.addPlugin(new AWSCognitoAuthPlugin());
                    Amplify.configure(getApplicationContext());
                    Amplify.Auth.signUp(
                            username.getText().toString(),
                            password.getText().toString(),
                            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email.getText().toString()).build(),
                            result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                            error -> Log.e("AuthQuickStart", "Sign up failed", error)

                    );
                    username.setText("");
                    password.setText("");
                    email.setText("");
                    Toast.makeText(getApplicationContext(),"Created Successfully please wait for AWS approval",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this,MainActivity.class);
                    startActivity(intent);

                }
                catch (Exception e){}
            }
        });

    }
}