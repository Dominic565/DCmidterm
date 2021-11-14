package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.api.RequestPlaceHolder;
import com.example.myapplication.api.RetrofitBuilder;
import com.example.myapplication.dominic.Login;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    public EditText username, password;
    public MaterialButton login;
    public RetrofitBuilder retrofitBuilder;
    public RequestPlaceHolder requestPlaceHolder;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.user);
        password =  findViewById(R.id.password);
        login = findViewById(R.id.login);
        retrofitBuilder = new RetrofitBuilder();
        requestPlaceHolder = retrofitBuilder.getRetrofit().create(RequestPlaceHolder.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Login> LoginCall = requestPlaceHolder.login(new Login(null, username.getText().toString(), null, null, password.getText().toString()));
                LoginCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "There was and error upon logging in the API", Toast.LENGTH_SHORT).show();
                            Log.e("Login_ERROR",response.message());
                        }else {
                            if(response.code()==200){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "There was and error upon logging in the API", Toast.LENGTH_SHORT).show();
                        Log.e("Login_ERROR",t.getMessage());
                    }
                });
            }
        });





        }
    }
