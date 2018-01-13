package com.example.aniketkumar.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by MNNIT on 1/11/2018.
 */

public class Login_Activity extends AppCompatActivity {

    Button login_button;
    TextView sign_up;
    TextView forgot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_button= (Button) findViewById(R.id.login_press);
        sign_up=findViewById(R.id.sign_link);
        forgot=findViewById(R.id.forgot);

        //On clicking the login button
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //On clicking the sign up link
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Activity.this,Sign_up.class);
                startActivity(intent);
                finish();
            }
        });

        //On clicking the forgot password link
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Activity.this,forgot_pass.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
