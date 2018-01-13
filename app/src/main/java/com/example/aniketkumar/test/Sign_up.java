package com.example.aniketkumar.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** Oncreate
 * Created by MNNIT on 1/14/2018.
 */

public class Sign_up extends AppCompatActivity {

    TextView login;
    Button sign_up;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        sign_up= (Button) findViewById(R.id.sign_press);
        login=findViewById(R.id.login_link);

        // On clicking the sign up button
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(  Sign_up.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //On clicking the login link
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up.this,Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
